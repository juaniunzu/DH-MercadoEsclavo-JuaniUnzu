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
import com.example.dh_mercadoesclavo.dao.ArticuloDao;
import com.example.dh_mercadoesclavo.model.Articulo;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ArticuloAdapter.ArticuloAdapterListener, ArticuloAdapter2.Articulo2AdapterListener {

    private RecyclerView fragmentHomeRecyclerViewRecientes;
    private RecyclerView fragmentHomeRecyclerViewRecomendados;
    private RecyclerView fragmentHomeRecyclerViewOfertas;
    private ArticuloHomeFragmentListener listener;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViewRecyclers(view);

        List<Articulo> listaRecientes = new ArrayList<>();
        listaRecientes.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos, "Juego de adornos navideños surtidos, consultar disponibilidad antes de ofertar."));

        ArticuloAdapter adapterRecyclerRecientes = new ArticuloAdapter(listaRecientes, this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        ArticuloAdapter2 adapterRecyclerRecomendados = new ArticuloAdapter2(ArticuloDao.getArticulos(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        fragmentHomeRecyclerViewRecomendados.setLayoutManager(linearLayoutManager);
        fragmentHomeRecyclerViewRecomendados.setAdapter(adapterRecyclerRecomendados);

        fragmentHomeRecyclerViewRecientes.setLayoutManager(manager);
        fragmentHomeRecyclerViewRecientes.setAdapter(adapterRecyclerRecientes);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (ArticuloHomeFragmentListener) context;
    }

    private void findViewRecyclers(View view) {
        fragmentHomeRecyclerViewRecientes = view.findViewById(R.id.fragmentHomeRecyclerViewRecientes);
        fragmentHomeRecyclerViewRecomendados = view.findViewById(R.id.fragmentHomeRecyclerViewRecomendados);
        fragmentHomeRecyclerViewOfertas = view.findViewById(R.id.fragmentHomeRecyclerViewOfertas);
    }

    @Override
    public void onClickArticulo(Articulo unArticulo) {
        listener.onClickArticuloFragmentHome(unArticulo);
    }

    @Override
    public void onClickAdapter2(Articulo unArticulo) {
        listener.onClickArticuloFragmentHome(unArticulo);
    }


    public interface ArticuloHomeFragmentListener{
        void onClickArticuloFragmentHome(Articulo unArticulo);
    }
}
