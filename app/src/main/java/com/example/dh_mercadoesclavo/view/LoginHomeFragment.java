package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.databinding.FragmentLoginHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginHomeFragment extends Fragment {

    private FragmentLoginHomeBinding binding;
    private LoginHomeFragmentListener listener;

    public LoginHomeFragment() {
        // Required empty public constructor
    }

    public LoginHomeFragment(LoginHomeFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.fragmentLoginHomeButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRegistrarse();
            }
        });

        binding.fragmentLoginHomeButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickIniciarSesion();
            }
        });

        return view;
    }

    public interface LoginHomeFragmentListener{
        void onClickIniciarSesion();
        void onClickRegistrarse();
    }

}
