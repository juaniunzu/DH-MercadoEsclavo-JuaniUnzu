package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.databinding.FragmentSignUpBinding;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private SignUpFragmentListener listener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public SignUpFragment(SignUpFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.fragmentSignupBotonSignupDeGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBotonSignUpGoogle();
            }
        });

        binding.fragmentSignupSignupButtonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBotonSignUpFacebook(binding.fragmentSignupSignupButtonFacebook);
            }
        });

        binding.fragmentSignupButtonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBotonSignUp(binding.fragmentSignupTextInputEditTextUsername.getText().toString(), binding.fragmentSignupTextInputEditTextPassword.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public interface SignUpFragmentListener {
        void onClickBotonSignUpGoogle();

        void onClickBotonSignUpFacebook(LoginButton loginButton);

        void onClickBotonSignUp(String usuario, String password);
    }
}
