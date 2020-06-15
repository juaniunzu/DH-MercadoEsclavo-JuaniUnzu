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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.dao.ArticuloFirestoreDao;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeFragment.ArticuloHomeFragmentListener, NavigationView.OnNavigationItemSelectedListener, PerfilFragment.PerfilFragmentListener {

    private DrawerLayout activityMainDrawerLayout;
    private NavigationView activityMainNavigationView;
    private Toolbar activityMainToolBar;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private Button loginButton;


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

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

    }

    private Boolean haySesionIniciada() {

        return (FirebaseAuth.getInstance().getCurrentUser() != null);
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
                FirebaseAuth client = FirebaseAuth.getInstance();
                if(client.getCurrentUser() == null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    pegarFragment(new PerfilFragment(MainActivity.this));
                    activityMainDrawerLayout.closeDrawers();
                }
                break;
            case R.id.navigationMenuPerfilCerrarSesion:
                Toast.makeText(this, "En construccion", Toast.LENGTH_SHORT).show();
                activityMainDrawerLayout.closeDrawers();

        }
        return false;
    }

    private Boolean cerrarSesionFirebaseAuth() {
        FirebaseAuth client = FirebaseAuth.getInstance();
        if(client.getCurrentUser() != null){
            Toast.makeText(this, client.getCurrentUser().getDisplayName() + ", te deslogueaste correctamente", Toast.LENGTH_SHORT).show();
            client.signOut();
            activityMainNavigationView.getHeaderView(0).setVisibility(View.GONE);
            activityMainNavigationView.inflateHeaderView(R.layout.nav_header_not_logged);
            return true;
        } else {
            return false;
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

    @Override
    public void onClickCerrarSesionPerfilFragment() {
        cerrarSesionFirebaseAuth();
        pegarFragment(new HomeFragment());
    }

    @Override
    public void onClickMisFavoritosPerfilFragment() {

        ArticuloController articuloController = new ArticuloController();
        articuloController.consultarArticulosEnFirebase(currentUser, new ResultListener<List<Articulo>>() {
            @Override
            public void onFinish(List<Articulo> result) {
                if(result.size() > 0){
                    Intent mainADetail = new Intent(MainActivity.this, DetailActivity.class);
                    Bundle mainADetalle = new Bundle();
                    mainADetalle.putSerializable("articulo", result.get(0));
                    mainADetalle.putSerializable("lista", (ArrayList) result);
                    mainADetail.putExtras(mainADetalle);
                    startActivity(mainADetail);
                } else {
                    Toast.makeText(MainActivity.this, "Todavia no agregaste favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
