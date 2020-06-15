package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh_mercadoesclavo.databinding.FragmentPerfilBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilFragmentListener listener;
    private FirebaseUser currentUser;

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

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        binding.layoutCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCerrarSesionPerfilFragment();
            }
        });

        binding.layoutFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickMisFavoritosPerfilFragment();
            }
        });

        if(currentUser.getDisplayName() != null){
            binding.textViewNombre.setText(currentUser.getDisplayName());
        } else {
            binding.textViewNombre.setVisibility(View.GONE);
        }

        binding.textViewMail.setText(currentUser.getEmail());





        return view;
    }

    public interface PerfilFragmentListener{
        void onClickCerrarSesionPerfilFragment();
        void onClickMisFavoritosPerfilFragment();
    }


}
