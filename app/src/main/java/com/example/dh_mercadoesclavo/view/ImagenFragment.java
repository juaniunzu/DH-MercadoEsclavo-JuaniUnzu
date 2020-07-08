package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dh_mercadoesclavo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImagenFragment extends Fragment {

    private ImageView imageView;


    public ImagenFragment() {
        // Required empty public constructor
    }

    public static ImagenFragment crearImagenFragment(String url){
        ImagenFragment fragment = new ImagenFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imagen, container, false);

        Bundle bundle = getArguments();
        imageView = view.findViewById(R.id.fragmentImagenImageView);
        String url = bundle.getString("url");
        Glide.with(getContext()).load(url).centerCrop().into(imageView);


        return view;
    }
}
