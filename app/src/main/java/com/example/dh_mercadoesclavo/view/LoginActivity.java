package com.example.dh_mercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dh_mercadoesclavo.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements SignUpFragment.SignUpFragmentListener, LoginHomeFragment.LoginHomeFragmentListener, LoginFragment.LoginFragmentListener {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "LOGIN_CON_FIREBASE";
    public static GoogleSignInClient client;
    public static CallbackManager callbackManager;
    private static final String EMAIL = "email";
    public static Boolean logueadoEnFacebook;
    public static GoogleSignInAccount account;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        client = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();

        mAuth = FirebaseAuth.getInstance();



        reemplazarFragment(new LoginHomeFragment(this));

    }

    private void reemplazarFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityLoginFragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        account = GoogleSignIn.getLastSignedInAccount(this);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        logueadoEnFacebook = accessToken != null && !accessToken.isExpired();

        //comprobar si ya hay usuario logueado, despues de este metodo habria q llamar updateUIFirebase(currentUser)
        FirebaseUser currentUser = mAuth.getCurrentUser();

        updateUI(account);

    }

    private void crearUsuario(String mail, String password){
        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUIFirebase(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    updateUIFirebase(null);
                }
            }
        });
    }

    private void iniciarSesion(String mail, String password){
        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUIFirebase(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    updateUIFirebase(null);
                }
            }
        });
    }

    private void updateUIFirebase(FirebaseUser firebaseUser){
        if(firebaseUser != null){
            Toast.makeText(this, "se logueo: " + firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        //hacer algo con la ui si el usuario esta logueado (si no esta logueado,
        //account = null).
        if(account != null || logueadoEnFacebook){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClickBotonSignUpGoogle() {
        signIn();
    }

    @Override
    public void onClickBotonSignUpFacebook(LoginButton loginButton) {
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Error! verificar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickBotonSignUp(String usuario, String password) {
        crearUsuario(usuario, password);
    }

    private void signIn() {
        Intent intent = client.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
                break;

        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //log.w imprime en Logcat.Warn
            Log.w("Google", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    public void onClickIniciarSesion() {
        reemplazarFragment(new LoginFragment(this));
    }

    @Override
    public void onClickRegistrarse() {
        reemplazarFragment(new SignUpFragment(this));
    }

    @Override
    public void onClickBotonLogInGoogle() {
        signIn();
    }

    @Override
    public void onClickBotonLogInFacebook(LoginButton loginButton) {
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Error! verificar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickBotonLogIn(String usuario, String password) {
        iniciarSesion(usuario, password);
    }
}
