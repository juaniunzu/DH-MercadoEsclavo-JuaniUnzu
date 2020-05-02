package com.example.dh_mercadoesclavo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticuloFragment extends Fragment {

    public ArticuloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //se infla el xml del fragment a View
        View view = inflater.inflate(R.layout.fragment_articulo, container, false);

        //se asocia el recyclerView del fragment con el elemento xml. Se hace como variable local
        //pero también se puede poner como atributo del fragment.
        RecyclerView fragmentArticuloRecyclerView = view.findViewById(R.id.fragmentArticuloRecyclerView);

        //creo lista que sera parametro en la construccion del adapter
        List<Articulo> listaDeArticulos = ProveedorDeArticulos.getArticulos();
        //creo adapter
        ArticuloAdapter articuloAdapter = new ArticuloAdapter(listaDeArticulos);
        //creo layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);

        //seteo adapter y layout manager al recyclerView
        fragmentArticuloRecyclerView.setLayoutManager(linearLayoutManager);
        fragmentArticuloRecyclerView.setAdapter(articuloAdapter);
        
        return view;
    }
}
