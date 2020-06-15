package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh_mercadoesclavo.databinding.FragmentPerfilBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilFragmentListener listener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public PerfilFragment(PerfilFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.layoutCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCerrarSesionPerfilFragment();
            }
        });





        return view;
    }

    public interface PerfilFragmentListener{
        void onClickCerrarSesionPerfilFragment();
    }


}
