package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;


public class DetailFragment extends Fragment {

    private ImageView fragmentDetailImageView;
    private TextView fragmentDetailTextViewNombre;
    private TextView fragmentDetailTextViewPrecio;
    private TextView fragmentDetailTextViewDescripcion;
    private Button fragmentDetailButtonComprar;
    private Button fragmentDetailButtonFavoritos;
    private RecyclerView fragmentDetailRecyclerViewRelacionados;



    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment crearDetailFragment(Articulo unArticulo){
        DetailFragment fragment = new DetailFragment();
        Bundle datosDeArticulo = new Bundle();
        datosDeArticulo.putSerializable("articulo", unArticulo);
        fragment.setArguments(datosDeArticulo);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        findViews(view);

        Bundle bundle = getArguments();
        Articulo articulo = (Articulo) bundle.getSerializable("articulo");

        Glide.with(getActivity()).load(articulo.getFoto()).centerCrop().into(fragmentDetailImageView);
        fragmentDetailTextViewNombre.setText(articulo.getTitle());
        fragmentDetailTextViewPrecio.setText(String.format(getContext().getResources().getString(R.string.valor), articulo.getPrice().toString()));
        fragmentDetailTextViewDescripcion.setText(articulo.getDescripcion());

        return view;
    }

    private void findViews(View view) {
        fragmentDetailImageView = view.findViewById(R.id.fragmentDetailImageView);
        fragmentDetailTextViewNombre = view.findViewById(R.id.fragmentDetailTextViewNombre);
        fragmentDetailTextViewPrecio = view.findViewById(R.id.fragmentDetailTextViewPrecio);
        fragmentDetailTextViewDescripcion = view.findViewById(R.id.fragmentDetailTextViewDescripcion);
        fragmentDetailButtonComprar = view.findViewById(R.id.fragmentDetailButtonComprar);
        fragmentDetailButtonFavoritos = view.findViewById(R.id.fragmentDetailButtonFavoritos);
        fragmentDetailRecyclerViewRelacionados = view.findViewById(R.id.fragmentDetailRecyclerViewRelacionados);
    }
}
