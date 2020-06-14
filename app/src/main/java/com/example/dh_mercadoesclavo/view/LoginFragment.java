package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.databinding.FragmentLoginBinding;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginFragmentListener listener;


    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment(LoginFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.fragmentLoginBotonLoginDeGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBotonLogInGoogle();
            }
        });

        binding.fragmentLoginLoginbuttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBotonLogInFacebook(binding.fragmentLoginLoginbuttonFacebook);
            }
        });

        binding.fragmentLoginButtonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBotonLogIn(binding.fragmentLoginTextInputEditTextUsername.getText().toString(), binding.fragmentLoginTextInputEditTextPassword.getText().toString());
            }
        });



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public interface LoginFragmentListener{
        void onClickBotonLogInGoogle();
        void onClickBotonLogInFacebook(LoginButton loginButton);
        void onClickBotonLogIn(String usuario, String password);
    }
}
