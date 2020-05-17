package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;


public class DetailFragment extends Fragment {

    private ImageView fragmentDetailImageView;
    private TextView fragmentDetailTextViewNombre;
    private TextView fragmentDetailTextViewPrecio;
    private TextView fragmentDetailTextViewDescripcion;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        fragmentDetailImageView = view.findViewById(R.id.fragmentDetailImageView);
        fragmentDetailTextViewNombre = view.findViewById(R.id.fragmentDetailTextViewNombre);
        fragmentDetailTextViewPrecio = view.findViewById(R.id.fragmentDetailTextViewPrecio);
        fragmentDetailTextViewDescripcion = view.findViewById(R.id.fragmentDetailTextViewDescripcion);

        Bundle desdeMain = getArguments();
        Articulo articulo = (Articulo) desdeMain.getSerializable("articulo");

        fragmentDetailImageView.setImageResource(articulo.getImagen());
        fragmentDetailTextViewNombre.setText(articulo.getNombre());
        fragmentDetailTextViewPrecio.setText(articulo.getPrecio());
        fragmentDetailTextViewDescripcion.setText(articulo.getDescripcion());

        return view;
    }
}
