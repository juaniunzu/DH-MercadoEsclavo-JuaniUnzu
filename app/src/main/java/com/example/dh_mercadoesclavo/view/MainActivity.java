package com.example.dh_mercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.view.ArticuloFragment;
import com.example.dh_mercadoesclavo.view.DetailFragment;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements HomeFragment.ArticuloHomeFragmentListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout activityMainDrawerLayout;
    private NavigationView activityMainNavigationView;
    private Toolbar activityMainToolBar;
    private FirebaseUser usuarioLogueado;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();

        configurarToolBar();

        activityMainNavigationView.setNavigationItemSelectedListener(this);

        HomeFragment fragment = new HomeFragment();
        pegarFragment(fragment);

        if (haySesionIniciada()) {
            activityMainNavigationView.inflateHeaderView(R.layout.nav_header_logged);
        } else {
            activityMainNavigationView.inflateHeaderView(R.layout.nav_header_not_logged);
        }

    }

    private Boolean haySesionIniciada() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        Boolean sesionEnGoogle = GoogleSignIn.getLastSignedInAccount(this) != null;
        Boolean sesionEnFacebook = accessToken != null && !accessToken.isExpired();
        return (sesionEnGoogle || sesionEnFacebook);
    }

    /**
     * configura la toolbar
     */
    private void configurarToolBar() {

        setSupportActionBar(activityMainToolBar);
        getSupportActionBar().setTitle("Mercado Esclavo");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, activityMainDrawerLayout, activityMainToolBar, R.string.open_drawers, R.string.close_drawers);
        activityMainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    /**
     * metodo que linkea los atributos del main con los elementos xml correspondientes
     */
    private void findViewsById() {
        activityMainToolBar = findViewById(R.id.activityMainToolBar);
        activityMainDrawerLayout = findViewById(R.id.activityMainDrawerLayout);
        activityMainNavigationView = findViewById(R.id.activityMainNavigationView);
    }

    /**
     * metodo que pega fragments en el main activity. Se le debe pasar como parametro el fragment deseado.
     *
     * @param unFragment
     */
    private void pegarFragment(Fragment unFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainFragmentContainer, unFragment).addToBackStack("add");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigationMenuAboutUs:
                pegarFragment(new AboutUsFragment());
                activityMainDrawerLayout.closeDrawers();
                break;
            case R.id.navigationMenuFavorite:
                Toast.makeText(this, R.string.en_construccion, Toast.LENGTH_SHORT).show();
                activityMainDrawerLayout.closeDrawers();
                break;
            case R.id.navigationMenuInicio:
                Toast.makeText(this, R.string.en_construccion, Toast.LENGTH_SHORT).show();
                activityMainDrawerLayout.closeDrawers();
            case R.id.topAppBarSearch:
                Toast.makeText(this, R.string.en_construccion, Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationMenuPerfil:
                //poner if que compruebe si esta logueado en algo
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.navigationMenuPerfilCerrarSesion:
                //cerrarSesionGoogle();
                cerrarSesionFacebook();
                cerrarSesionFirebaseAuth();
                activityMainNavigationView.getHeaderView(0).setVisibility(View.GONE);
                activityMainNavigationView.inflateHeaderView(R.layout.nav_header_not_logged);
                activityMainDrawerLayout.closeDrawers();
        }
        return false;
    }

    private void cerrarSesionFacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null && !accessToken.isExpired()) {
            LoginManager.getInstance().logOut();
            Toast.makeText(this, "Te deslogueaste de facebook", Toast.LENGTH_SHORT).show();
        }
    }

    private void cerrarSesionFirebaseAuth() {
        FirebaseAuth client = FirebaseAuth.getInstance();
        Toast.makeText(this, client.getCurrentUser().getDisplayName() + ", te deslogueaste correctamente", Toast.LENGTH_SHORT).show();
        client.signOut();
    }

    private void cerrarSesionGoogle() {
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            LoginActivity.client.signOut().addOnCanceledListener(new OnCanceledListener() {
                @Override
                public void onCanceled() {
                    Toast.makeText(MainActivity.this, "Cancelaste el logout", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MainActivity.this, "Te deslogueaste de google", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onNavigationItemSelected(item);
        return true;
    }

    @Override
    public void onClickHomeFragmentRecomendados(Articulo unArticulo, List<Articulo> articuloList) {

        Intent mainADetail = new Intent(this, DetailActivity.class);
        Bundle mainADetalle = new Bundle();
        mainADetalle.putSerializable("articulo", unArticulo);
        mainADetalle.putSerializable("lista", (ArrayList) articuloList);
        mainADetail.putExtras(mainADetalle);
        startActivity(mainADetail);

    }

    @Override
    public void onClickHomeFragmentPorqueVisitaste(Articulo articulo, List<Articulo> articuloList) {
        Intent mainADetail = new Intent(this, DetailActivity.class);
        Bundle mainADetalle = new Bundle();
        mainADetalle.putSerializable("articulo", articulo);
        mainADetalle.putSerializable("lista", (ArrayList) articuloList);
        mainADetail.putExtras(mainADetalle);
        startActivity(mainADetail);
    }

    @Override
    public void onClickHomeFragmentFavorito(Articulo articulo, List<Articulo> articuloList) {
        Intent mainADetail = new Intent(this, DetailActivity.class);
        Bundle mainADetalle = new Bundle();
        mainADetalle.putSerializable("articulo", articulo);
        mainADetalle.putSerializable("lista", (ArrayList) articuloList);
        mainADetail.putExtras(mainADetalle);
        startActivity(mainADetail);
    }

    @Override
    public void onClickHomeFragmentElegidos(Articulo articulo, List<Articulo> articuloList) {
        Intent mainADetail = new Intent(this, DetailActivity.class);
        Bundle mainADetalle = new Bundle();
        mainADetalle.putSerializable("articulo", articulo);
        mainADetalle.putSerializable("lista", (ArrayList) articuloList);
        mainADetail.putExtras(mainADetalle);
        startActivity(mainADetail);
    }
}
