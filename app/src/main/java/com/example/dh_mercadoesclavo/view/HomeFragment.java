package com.example.dh_mercadoesclavo.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.databinding.FragmentHomeBinding;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterElegidos;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterRecomendados;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ArticuloAdapterRecomendados.ArticuloAdapterRecomendadosListener, ArticuloAdapterElegidos.ArticuloAdapterElegidosListener {


    private RecyclerView fragmentHomeRecyclerViewRecomendados;
    private ArticuloHomeFragmentListener listener;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private FragmentHomeBinding binding;
    private ArticuloAdapterRecomendados adapterRecyclerRecomendados;
    private ArticuloController articuloController;
    private ImageView mostrarFiltro;
    private TextView filtraTitulo;
    private TextView textViewentre;
    private EditText editTextDesde;
    private EditText editTextHasta;
    private TextView textViewY;
    private Button aplicarFiltro;
    private ImageView paginaAnterior;
    private ImageView paginaSiguiente;
    private TextView textViewCantidadPaginas;
    public static final String SEARCH_TEXT = "fender jazz bass";
    private int paginaActual = 1;
    private int totalPaginas;
    private String query;
    public static final int PAGE_SIZE = 10;
    private int offset = 0;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        findViews();

        adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(new ArrayList<Articulo>(), HomeFragment.this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
        fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);

        filtraTitulo.setVisibility(View.GONE);
        textViewentre.setVisibility(View.GONE);
        editTextDesde.setVisibility(View.GONE);
        editTextHasta.setVisibility(View.GONE);
        textViewY.setVisibility(View.GONE);
        aplicarFiltro.setVisibility(View.GONE);

        mostrarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filtraTitulo.getVisibility() == View.GONE){
                    filtraTitulo.setVisibility(View.VISIBLE);
                    textViewentre.setVisibility(View.VISIBLE);
                    editTextDesde.setVisibility(View.VISIBLE);
                    editTextHasta.setVisibility(View.VISIBLE);
                    textViewY.setVisibility(View.VISIBLE);
                    aplicarFiltro.setVisibility(View.VISIBLE);
                } else {
                    filtraTitulo.setVisibility(View.GONE);
                    textViewentre.setVisibility(View.GONE);
                    editTextDesde.setVisibility(View.GONE);
                    editTextHasta.setVisibility(View.GONE);
                    textViewY.setVisibility(View.GONE);
                    aplicarFiltro.setVisibility(View.GONE);
                }
            }
        });

        aplicarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset = 0;
                paginaActual = 1;

                if(editTextDesde.getText() != null && editTextHasta.getText() != null && !editTextDesde.getText().toString().isEmpty() && !editTextHasta.getText().toString().isEmpty()){
                    Integer precioDesde = Integer.parseInt(editTextDesde.getText().toString());
                    Integer precioHasta = Integer.parseInt(editTextHasta.getText().toString());

                    if((precioDesde >= precioHasta) || (precioDesde == 0 || precioHasta == 0)){
                        Toast.makeText(getContext(), "Ingrese valores válidos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    query = precioDesde + ".00" + "-" + precioHasta + ".00";
                    articuloController.getItemsPorQuery(SEARCH_TEXT, PAGE_SIZE, offset, query, new ResultListener<ArticuloContainer>() {
                        @Override
                        public void onFinish(ArticuloContainer result) {
                            if(result.getPaging().getTotal()%PAGE_SIZE != 0){
                                totalPaginas = result.getPaging().getTotal()/PAGE_SIZE + 1;
                                paginaAnterior.setVisibility(View.VISIBLE);
                                paginaSiguiente.setVisibility(View.VISIBLE);
                                textViewCantidadPaginas.setVisibility(View.VISIBLE);
                            } else if(result.getPaging().getTotal() == 0){
                                paginaAnterior.setVisibility(View.GONE);
                                paginaSiguiente.setVisibility(View.GONE);
                                textViewCantidadPaginas.setVisibility(View.GONE);
                            } else {
                                totalPaginas = result.getPaging().getTotal()/PAGE_SIZE;
                                paginaAnterior.setVisibility(View.VISIBLE);
                                paginaSiguiente.setVisibility(View.VISIBLE);
                                textViewCantidadPaginas.setVisibility(View.VISIBLE);
                            }
                            adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(result.getResults(), HomeFragment.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
                            fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);
                            textViewCantidadPaginas.setText(paginaActual + " de " + totalPaginas);
                        }
                    });

                } else {
                    Toast.makeText(getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        paginaAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offset == 0 || paginaActual == 1){
                    Toast.makeText(getContext(), "Estás en la primera página", Toast.LENGTH_SHORT).show();
                } else {
                    offset -= PAGE_SIZE;
                    paginaActual--;
                    articuloController.getItemsPorQuery(SEARCH_TEXT, PAGE_SIZE, offset, query, new ResultListener<ArticuloContainer>() {
                        @Override
                        public void onFinish(ArticuloContainer result) {
                            if(result.getPaging().getTotal()%PAGE_SIZE != 0){
                                totalPaginas = result.getPaging().getTotal()/PAGE_SIZE + 1;
                            } else {
                                totalPaginas = result.getPaging().getTotal()/PAGE_SIZE;
                            }

                            adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(result.getResults(), HomeFragment.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
                            fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);
                            textViewCantidadPaginas.setText(paginaActual + " de " + totalPaginas);
                        }
                    });
                }
            }
        });

        paginaSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paginaActual == totalPaginas){
                    Toast.makeText(getContext(), "Estás en la ultima página", Toast.LENGTH_SHORT).show();
                } else {
                    offset += PAGE_SIZE;
                    paginaActual++;
                    articuloController.getItemsPorQuery(SEARCH_TEXT, PAGE_SIZE, offset, query, new ResultListener<ArticuloContainer>() {
                        @Override
                        public void onFinish(ArticuloContainer result) {
                            if(result.getPaging().getTotal()%PAGE_SIZE != 0){
                                totalPaginas = result.getPaging().getTotal()/PAGE_SIZE + 1;
                            } else {
                                totalPaginas = result.getPaging().getTotal()/PAGE_SIZE;
                            }

                            adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(result.getResults(), HomeFragment.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
                            fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);
                            textViewCantidadPaginas.setText(paginaActual + " de " + totalPaginas);
                        }
                    });
                }
            }
        });


        articuloController = new ArticuloController();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        setRecomendadosRecyclerView();

        return view;
    }


    private void setRecomendadosRecyclerView() {
        articuloController.getItemsPorQuery(SEARCH_TEXT, PAGE_SIZE, offset, null, new ResultListener<ArticuloContainer>() {
            @Override
            public void onFinish(ArticuloContainer result) {
                if(result.getPaging().getTotal()%PAGE_SIZE != 0){
                    totalPaginas = result.getPaging().getTotal()/PAGE_SIZE + 1;
                } else {
                    totalPaginas = result.getPaging().getTotal()/PAGE_SIZE;
                }
                adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(result.getResults(), HomeFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
                fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);
                textViewCantidadPaginas.setText(paginaActual + " de " + totalPaginas);
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (ArticuloHomeFragmentListener) context;
    }

    private void findViews() {
        fragmentHomeRecyclerViewRecomendados = binding.fragmentHomeRecyclerViewRecomendados;
        mostrarFiltro = binding.fragmentHomeImageViewFiltrar;
        filtraTitulo = binding.fragmentHomeTextViewRango;
        textViewentre = binding.fragmentHomeTextViewEntre;
        editTextDesde = binding.fragmentHomeEditTextMenorQue;
        editTextHasta = binding.fragmentHomeEditTextMayorQue;
        textViewY = binding.fragmentHomeTextViewY;
        aplicarFiltro = binding.fragmentHomeButtonAplicarFiltro;
        paginaAnterior = binding.fragmentHomePaginadoAnterior;
        paginaSiguiente = binding.fragmentHomePaginadoSiguiente;
        textViewCantidadPaginas = binding.fragmentHomeTextViewPaginado;
    }

    @Override
    public void onClickAdapterRecomendados(Articulo unArticulo, List<Articulo> articuloList) {
        listener.onClickHomeFragmentRecomendados(unArticulo, articuloList);
    }

    @Override
    public void onClickAdapterElegidos(Articulo articulo, List<Articulo> articuloList) {
        listener.onClickHomeFragmentElegidos(articulo, articuloList);
    }


    public interface ArticuloHomeFragmentListener {
        void onClickHomeFragmentRecomendados(Articulo unArticulo, List<Articulo> articuloList);
        void onClickHomeFragmentElegidos(Articulo articulo, List<Articulo> articuloList);
    }
}
