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

import com.example.dh_mercadoesclavo.dao.ArticuloDao;
import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticuloFragment extends Fragment implements ArticuloAdapter.ArticuloAdapterListener {

    private ArticuloFragmentListener articuloFragmentListener;


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
        List<Articulo> listaDeArticulos = ArticuloDao.getArticulos();
        //creo adapter
        ArticuloAdapter articuloAdapter = new ArticuloAdapter(listaDeArticulos, this);
        //creo layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);

        //seteo adapter y layout manager al recyclerView
        fragmentArticuloRecyclerView.setLayoutManager(linearLayoutManager);
        fragmentArticuloRecyclerView.setAdapter(articuloAdapter);
        
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.articuloFragmentListener = (ArticuloFragmentListener) context;
    }

    @Override
    public void onClickArticulo(Articulo unArticulo) {
        this.articuloFragmentListener.onClickArticuloFragment(unArticulo);
    }

    public interface ArticuloFragmentListener {
        void onClickArticuloFragment(Articulo unArticulo);
    }
}
