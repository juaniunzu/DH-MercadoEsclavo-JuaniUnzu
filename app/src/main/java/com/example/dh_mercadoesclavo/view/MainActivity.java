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
import android.widget.Toast;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.databinding.ActivityMainBinding;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.CategoriaPadre;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.example.dh_mercadoesclavo.util.Utils;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity implements HomeFragment.ArticuloHomeFragmentListener, NavigationView.OnNavigationItemSelectedListener, PerfilFragment.PerfilFragmentListener, DetailIndividualFragment.DetailIndividualFragmentListener, FavoritosFragment.FavoritosFragmentListener {

    private DrawerLayout activityMainDrawerLayout;
    private NavigationView activityMainNavigationView;
    private Toolbar activityMainToolBar;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        findViews();

        configurarToolBar();

        activityMainNavigationView.setNavigationItemSelectedListener(this);

        HomeFragment fragment = new HomeFragment();
        reemplazarFragment(fragment);


        if (Utils.haySesionIniciada()) {
            activityMainNavigationView.inflateHeaderView(R.layout.nav_header_logged);
        } else {
            activityMainNavigationView.inflateHeaderView(R.layout.nav_header_not_logged);
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

    }

    private void findViews() {
        activityMainToolBar = findViewById(R.id.activityMainToolBar);
        activityMainDrawerLayout = binding.activityMainDrawerLayout;
        activityMainNavigationView = binding.activityMainNavigationView;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void configurarToolBar() {

        setSupportActionBar(activityMainToolBar);
        getSupportActionBar().setTitle("Mercado Esclavo");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, activityMainDrawerLayout, activityMainToolBar, R.string.open_drawers, R.string.close_drawers);
        activityMainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    private void reemplazarFragment(Fragment unFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainFragmentContainer, unFragment).addToBackStack("add");
        fragmentTransaction.commit();
    }

    private void pegarFragment(Fragment unFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activityMainFragmentContainer, unFragment).addToBackStack("add");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigationMenuMaps:
                Intent intent2 = new Intent(this, MapsActivity.class);
                startActivity(intent2);
                activityMainDrawerLayout.closeDrawers();
                break;
            case R.id.navigationMenuAboutUs:
                reemplazarFragment(new AboutUsFragment());
                activityMainDrawerLayout.closeDrawers();
                break;
            case R.id.navigationMenuInicio:
                reemplazarFragment(new HomeFragment());
                activityMainDrawerLayout.closeDrawers();
            case R.id.topAppBarSearch:
                break;
            case R.id.navigationMenuPerfil:
                if (!Utils.haySesionIniciada()) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    reemplazarFragment(new PerfilFragment(MainActivity.this));
                    activityMainDrawerLayout.closeDrawers();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onNavigationItemSelected(item);
        return true;
    }

    private void cerrarSesionFirebaseAuth() {
        FirebaseAuth client = FirebaseAuth.getInstance();
        if (Utils.haySesionIniciada()) {
            Toast.makeText(this, "Te deslogueaste correctamente", Toast.LENGTH_SHORT).show();
            client.signOut();
            activityMainNavigationView.getHeaderView(0).setVisibility(View.GONE);
            activityMainNavigationView.inflateHeaderView(R.layout.nav_header_not_logged);
        }
    }

    @Override
    public void onClickHomeFragmentRecomendados(Articulo unArticulo, List<Articulo> articuloList) {
        pegarFragment(DetailIndividualFragment.crearDetailIndividualFragment(unArticulo, this));
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
        reemplazarFragment(new HomeFragment());
    }

    @Override
    public void onClickMisFavoritosPerfilFragment() {

        ArticuloController articuloController = new ArticuloController();
        articuloController.consultarArticulosEnFirebase(currentUser, new ResultListener<List<Articulo>>() {
            @Override
            public void onFinish(List<Articulo> result) {
                if (result.size() > 0) {

                    pegarFragment(new FavoritosFragment(result, MainActivity.this));

                    /*Intent mainADetail = new Intent(MainActivity.this, DetailActivity.class);
                    Bundle mainADetalle = new Bundle();
                    mainADetalle.putSerializable("articulo", result.get(0));
                    mainADetalle.putSerializable("lista", (ArrayList) result);
                    mainADetail.putExtras(mainADetalle);
                    startActivity(mainADetail);*/
                } else {
                    Toast.makeText(MainActivity.this, "Todavia no agregaste favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClickButtonFavoritosDetailIndividualFragment(final Articulo articulo) {
        final ArticuloController articuloController = new ArticuloController();
        if (Utils.haySesionIniciada()) {
            articuloController.getItemsPorId(articulo.getId(), new ResultListener<Articulo>() {
                @Override
                public void onFinish(Articulo result) {
                    articuloController.agregarArticuloAFirebase(result, currentUser, new ResultListener<Articulo>() {
                        @Override
                        public void onFinish(Articulo result) {
                            Toast.makeText(MainActivity.this, "Se agregó el artículo a favoritos", Toast.LENGTH_SHORT).show();
                        }
                    });
                    articuloController.getCategoriasPorId(result.getCategoryId(), new ResultListener<CategoriaPadre>() {
                        @Override
                        public void onFinish(CategoriaPadre result) {
                            articuloController.agregarCategoriaFavoritaAFirebase(result, currentUser, new ResultListener<CategoriaPadre>() {
                                @Override
                                public void onFinish(CategoriaPadre result) {
                                    Toast.makeText(MainActivity.this, "Se agrego la categoria " + result.getName() + " a favoritos", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                }
            });
        } else {
            Toast.makeText(this, "Para agregar a favoritos, inicia sesion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClickButtonUbicacionDetailIndividualFragment(Articulo articulo) {
        Intent mainAMaps = new Intent(this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("articulo", articulo);
        mainAMaps.putExtras(bundle);
        startActivity(mainAMaps);
    }

    @Override
    public void onClickArticuloFavoritosFragment(Articulo articulo) {
        Intent mainADetail = new Intent(this, DetailActivity.class);
        Bundle mainADetalle = new Bundle();
        mainADetalle.putSerializable("articulo", articulo);
        mainADetail.putExtras(mainADetalle);
        startActivity(mainADetail);
    }
}
