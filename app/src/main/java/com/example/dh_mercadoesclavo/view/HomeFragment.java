package com.example.dh_mercadoesclavo.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterElegidos;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterFavorito;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterPorqueVisitaste;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterReciente;
import com.example.dh_mercadoesclavo.view.adapter.ArticuloAdapterRecomendados;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment implements ArticuloAdapterRecomendados.ArticuloAdapterRecomendadosListener,
        ArticuloAdapterPorqueVisitaste.ArticuloAdapterPorqueVisitasteListener, ArticuloAdapterFavorito.ArticuloAdapterFavoritoListener, ArticuloAdapterElegidos.ArticuloAdapterElegidosListener {

    private RecyclerView fragmentHomeRecyclerViewRecientes;
    private RecyclerView fragmentHomeRecyclerViewRecomendados;
    private RecyclerView fragmentHomeRecyclerViewPorqueVisitaste;
    private RecyclerView fragmentHomeRecyclerViewFavorito;
    private RecyclerView fragmentHomeRecyclerViewElegidos;
    private ArticuloHomeFragmentListener listener;
    private TextView fragmentHomeCardViewRecientesTextViewHistorial;
    private TextView fragmentHomeCardViewRecomendadosTextViewVerMas;
    private TextView fragmentHomeCardViewPorqueVisitasteTextViewVerMas;
    private TextView fragmentHomeCardViewFavoritoTextViewVerMas;
    private TextView fragmentHomeCardViewElegidosTextViewVerMas;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private CardView fragmentHomeCardViewFavorito;
    private CardView fragmentHomeCardViewPorqueVisitaste;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        findViewRecyclers(view);

        setRecientesRecyclerView();

        setRecomendadosRecyclerView();

        setPorqueVisitasteRecyclerView();

        setFavoritoRecyclerView();

        setElegidosRecyclerView();


        fragmentHomeCardViewRecomendadosTextViewVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamar el listener de este fragment y pasarle como lista una nueva call con una query a configurar
            }
        });

        return view;
    }

    private void setElegidosRecyclerView() {
        ArticuloController articuloController = new ArticuloController();
        articuloController.getFender(new ResultListener<List<Articulo>>() {
            @Override
            public void onFinish(List<Articulo> result) {
                ArticuloAdapterFavorito articuloAdapterFavorito = new ArticuloAdapterFavorito(result, HomeFragment.this);
                LinearLayoutManager llm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                fragmentHomeRecyclerViewElegidos.setAdapter(articuloAdapterFavorito);
                fragmentHomeRecyclerViewElegidos.setLayoutManager(llm);
            }
        });
    }

    private void setFavoritoRecyclerView() {

        ArticuloController articuloController = new ArticuloController();
        articuloController.getFender(new ResultListener<List<Articulo>>() {
            @Override
            public void onFinish(List<Articulo> result) {
                ArticuloAdapterFavorito articuloAdapterFavorito = new ArticuloAdapterFavorito(result, HomeFragment.this);
                LinearLayoutManager llm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                fragmentHomeRecyclerViewFavorito.setAdapter(articuloAdapterFavorito);
                fragmentHomeRecyclerViewFavorito.setLayoutManager(llm);
            }
        });
    }

    private void setPorqueVisitasteRecyclerView() {
        ArticuloController articuloController = new ArticuloController();
        articuloController.getItemsPorQuery("gibson bass", 10, new ResultListener<ArticuloContainer>() {
            @Override
            public void onFinish(ArticuloContainer result) {
                ArticuloAdapterPorqueVisitaste articuloAdapterPorqueVisitaste = new ArticuloAdapterPorqueVisitaste(result.getResults(), HomeFragment.this);
                GridLayoutManager glm = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
                fragmentHomeRecyclerViewPorqueVisitaste.setLayoutManager(glm);
                fragmentHomeRecyclerViewPorqueVisitaste.setAdapter(articuloAdapterPorqueVisitaste);
            }
        });
    }

    private void setRecomendadosRecyclerView() {
        final ArticuloController articuloController = new ArticuloController();
        articuloController.getFender(new ResultListener<List<Articulo>>() {
            @Override
            public void onFinish(List<Articulo> result) {

                ArticuloAdapterRecomendados adapterRecyclerRecomendados = new ArticuloAdapterRecomendados(result, HomeFragment.this);
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
        fragmentHomeCardViewFavorito = view.findViewById(R.id.fragmentHomeCardViewFavorito);
        if(currentUser == null){
            fragmentHomeCardViewFavorito.setVisibility(View.GONE);
        }
        fragmentHomeCardViewPorqueVisitaste = view.findViewById(R.id.fragmentHomeCardViewPorqueVisitaste);
        if(currentUser == null){
            fragmentHomeCardViewPorqueVisitaste.setVisibility(View.GONE);
        }
        fragmentHomeRecyclerViewRecientes = view.findViewById(R.id.fragmentHomeRecyclerViewRecientes);
        fragmentHomeRecyclerViewRecomendados = view.findViewById(R.id.fragmentHomeRecyclerViewRecomendados);
        fragmentHomeRecyclerViewPorqueVisitaste = view.findViewById(R.id.fragmentHomeRecyclerViewPorqueVisitaste);
        fragmentHomeRecyclerViewFavorito = view.findViewById(R.id.fragmentHomeRecyclerViewFavorito);
        fragmentHomeRecyclerViewElegidos = view.findViewById(R.id.fragmentHomeRecyclerViewElegidos);
        fragmentHomeCardViewRecientesTextViewHistorial = view.findViewById(R.id.fragmentHomeCardViewRecientesTextViewHistorial);
        fragmentHomeCardViewRecomendadosTextViewVerMas = view.findViewById(R.id.fragmentHomeCardViewRecomendadosTextViewVerMas);
        fragmentHomeCardViewPorqueVisitasteTextViewVerMas = view.findViewById(R.id.fragmentHomeCardViewPorqueVisitasteTextViewVerMas);
        fragmentHomeCardViewFavoritoTextViewVerMas = view.findViewById(R.id.fragmentHomeCardViewFavoritoTextViewVerMas);
        fragmentHomeCardViewElegidosTextViewVerMas = view.findViewById(R.id.fragmentHomeCardViewElegidosTextViewVerMas);
    }

    @Override
    public void onClickAdapterRecomendados(Articulo unArticulo, List<Articulo> articuloList) {
        listener.onClickHomeFragmentRecomendados(unArticulo, articuloList);
    }

    @Override
    public void onClickAdapterPorqueVisitaste(Articulo articulo, List<Articulo> articuloList) {
        listener.onClickHomeFragmentPorqueVisitaste(articulo, articuloList);
    }

    @Override
    public void onClickAdapterFavorito(Articulo articulo, List<Articulo> articuloList) {
        listener.onClickHomeFragmentFavorito(articulo, articuloList);
    }

    @Override
    public void onClickAdapterElegidos(Articulo articulo, List<Articulo> articuloList) {
        listener.onClickHomeFragmentElegidos(articulo, articuloList);
    }


    public interface ArticuloHomeFragmentListener{
        void onClickHomeFragmentRecomendados(Articulo unArticulo, List<Articulo> articuloList);
        void onClickHomeFragmentPorqueVisitaste(Articulo articulo, List<Articulo> articuloList);
        void onClickHomeFragmentFavorito(Articulo articulo, List<Articulo> articuloList);
        void onClickHomeFragmentElegidos(Articulo articulo, List<Articulo> articuloList);
    }
}
