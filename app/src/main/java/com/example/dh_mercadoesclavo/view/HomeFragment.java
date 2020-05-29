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

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.util.ResultListener;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment implements ArticuloAdapterRecomendados.Articulo2AdapterListener {

    private RecyclerView fragmentHomeRecyclerViewRecientes;
    private RecyclerView fragmentHomeRecyclerViewRecomendados;
    private RecyclerView fragmentHomeRecyclerViewPorqueVisitaste;
    private RecyclerView fragmentHomeRecyclerViewFavorito;
    private RecyclerView fragmentHomeRecyclerViewElegidos;
    private ArticuloHomeFragmentListener listener;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViewRecyclers(view);

        setRecientesRecyclerView();

        setRecomendadosRecyclerView();

        return view;
    }

    private void setRecomendadosRecyclerView() {
        ArticuloController articuloController = new ArticuloController();
        articuloController.getFender(new ResultListener<ArticuloContainer>() {
            @Override
            public void onFinish(ArticuloContainer result) {

                ArticuloAdapterRecomendados adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(result.getResults(), HomeFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
                fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);


            }
        });
    }

    private void setRecientesRecyclerView() {
        List<Articulo> listaRecientes = new ArrayList<>();
        listaRecientes.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos, "Juego de adornos navideños surtidos, consultar disponibilidad antes de ofertar."));

        ArticuloAdapterReciente articuloAdapterReciente = new ArticuloAdapterReciente(listaRecientes);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        fragmentHomeRecyclerViewRecientes.setLayoutManager(manager);
        fragmentHomeRecyclerViewRecientes.setAdapter(articuloAdapterReciente);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (ArticuloHomeFragmentListener) context;
    }

    private void findViewRecyclers(View view) {
        fragmentHomeRecyclerViewRecientes = view.findViewById(R.id.fragmentHomeRecyclerViewRecientes);
        fragmentHomeRecyclerViewRecomendados = view.findViewById(R.id.fragmentHomeRecyclerViewRecomendados);
        fragmentHomeRecyclerViewPorqueVisitaste = view.findViewById(R.id.fragmentHomeRecyclerViewPorqueVisitaste);
        fragmentHomeRecyclerViewFavorito = view.findViewById(R.id.fragmentHomeRecyclerViewFavorito);
        fragmentHomeRecyclerViewElegidos = view.findViewById(R.id.fragmentHomeRecyclerViewElegidos);
    }

    @Override
    public void onClickAdapter2(Articulo unArticulo, List<Articulo> articuloList) {
        listener.onClickArticuloFragmentHome(unArticulo, articuloList);
    }


    public interface ArticuloHomeFragmentListener{
        void onClickArticuloFragmentHome(Articulo unArticulo, List<Articulo> articuloList);
    }
}
