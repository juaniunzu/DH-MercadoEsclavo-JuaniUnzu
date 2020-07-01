package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.view.adapter.FavoritosAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements FavoritosAdapter.FavoritosAdapterListener {

    private List<Articulo> articuloList;
    private RecyclerView recyclerView;
    private FavoritosFragmentListener listener;


    public FavoritosFragment() {
        // Required empty public constructor
    }

    public FavoritosFragment(List<Articulo> articuloList, FavoritosFragmentListener listener) {
        this.articuloList = articuloList;
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerView = view.findViewById(R.id.fragmentFavoritosRecyclerView);

        FavoritosAdapter favoritosAdapter = new FavoritosAdapter(articuloList, this);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(favoritosAdapter);


        return view;
    }

    @Override
    public void onClickArticuloFavoritosAdapter(Articulo articulo) {
        this.listener.onClickArticuloFavoritosFragment(articulo);
    }

    public interface FavoritosFragmentListener{
        void onClickArticuloFavoritosFragment(Articulo articulo);
    }
}
