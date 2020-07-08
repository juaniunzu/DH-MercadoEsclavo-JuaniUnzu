package com.example.dh_mercadoesclavo.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.databinding.FragmentHomeBinding;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.CategoriaPadre;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.example.dh_mercadoesclavo.util.Utils;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterElegidos;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterRecomendados;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ArticuloAdapterRecomendados.ArticuloAdapterRecomendadosListener, ArticuloAdapterElegidos.ArticuloAdapterElegidosListener {


    private RecyclerView fragmentHomeRecyclerViewRecomendados;
    private RecyclerView fragmentHomeRecyclerViewElegidos;
    private ArticuloHomeFragmentListener listener;
    private CardView elegidos;
    private TextView fragmentHomeCardViewElegidosTextViewVerMas;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private FragmentHomeBinding binding;
    private CardView agregaFavoritos;
    private CardView iniciaSesion;
    private ArticuloAdapterRecomendados adapterRecyclerRecomendados;
    private ArticuloController articuloController;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        articuloController = new ArticuloController();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        findViews();

        agregaFavoritos.setVisibility(View.GONE);

        adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(new ArrayList<Articulo>(), HomeFragment.this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
        fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);



        setRecomendadosRecyclerView();

        if(Utils.haySesionIniciada()){
            iniciaSesion.setVisibility(View.GONE);
            setElegidosRecyclerView();
        } else {
            elegidos.setVisibility(View.GONE);
        }

        return view;
    }

    private void setElegidosRecyclerView() {
        articuloController.consultarCategoriasEnFirebase(currentUser, new ResultListener<List<CategoriaPadre>>() {

            @Override
            public void onFinish(List<CategoriaPadre> resulta) {
                if(resulta.size() > 0){
                    articuloController.getItemsPorQuery("", 10, 0, resulta.get(0).getPathFromRoot().get(0).getId(), new ResultListener<List<Articulo>>() {

                        @Override
                        public void onFinish(List<Articulo> result) {

                            ArticuloAdapterElegidos articuloAdapterElegidos = new ArticuloAdapterElegidos(result, HomeFragment.this);
                            LinearLayoutManager llm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            fragmentHomeRecyclerViewElegidos.setAdapter(articuloAdapterElegidos);
                            fragmentHomeRecyclerViewElegidos.setLayoutManager(llm);

                        }
                    });
                } else {
                    elegidos.setVisibility(View.GONE);
                    agregaFavoritos.setVisibility(View.VISIBLE);

                }

            }
        });


    }


    private void setRecomendadosRecyclerView() {
        if(articuloController.getHayMasResultados()){
            articuloController.getItemsPorQueryPaginado("fender jazz bass", null, new ResultListener<List<Articulo>>() {
                @Override
                public void onFinish(List<Articulo> result) {

                    adapterRecyclerRecomendados.addArticuloList(result);

                }
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (ArticuloHomeFragmentListener) context;
    }

    private void findViews() {

        elegidos = binding.fragmentHomeCardViewElegidos;
        fragmentHomeRecyclerViewRecomendados = binding.fragmentHomeRecyclerViewRecomendados;
        fragmentHomeRecyclerViewElegidos = binding.fragmentHomeRecyclerViewElegidos;
        agregaFavoritos = binding.fragmentHomeCardViewAgregaFavoritos;
        iniciaSesion = binding.fragmentHomeCardViewIniciaSesion;
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
