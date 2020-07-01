package com.example.dh_mercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.dao.ArticuloFirestoreDao;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.CategoriaPadre;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailIndividualFragment.DetailIndividualFragmentListener {

    private Toolbar toolbar;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.activityDetailToolBar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        Intent desdeMain = getIntent();
        Bundle datosDesdeMain = desdeMain.getExtras();

        Articulo articuloClickeado = (Articulo) datosDesdeMain.getSerializable("articulo");
        ArticuloController articuloController = new ArticuloController();
        articuloController.getItemsPorId(articuloClickeado.getId(), new ResultListener<Articulo>() {
            @Override
            public void onFinish(Articulo result) {
                pegarFragment(DetailIndividualFragment.crearDetailIndividualFragment(result, DetailActivity.this));
            }
        });



        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

    }

    private void pegarFragment(Fragment unFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activityDetailFragmentContainer, unFragment).addToBackStack("add");
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }

        return true;
    }


    @Override
    public void onClickButtonFavoritosDetailIndividualFragment(Articulo articulo) {
        final ArticuloController articuloController = new ArticuloController();
        if(currentUser != null){

            articuloController.getItemsPorId(articulo.getId(), new ResultListener<Articulo>() {
                @Override
                public void onFinish(Articulo result) {
                    articuloController.agregarArticuloAFirebase(result, currentUser, new ResultListener<Articulo>() {
                        @Override
                        public void onFinish(Articulo result) {
                            Toast.makeText(DetailActivity.this, "Se agregó el artículo a favoritos", Toast.LENGTH_SHORT).show();
                        }
                    });
                    articuloController.getCategoriasPorId(result.getCategoryId(), new ResultListener<CategoriaPadre>() {
                        @Override
                        public void onFinish(CategoriaPadre result) {
                            articuloController.agregarCategoriaFavoritaAFirebase(result, currentUser, new ResultListener<CategoriaPadre>() {
                                @Override
                                public void onFinish(CategoriaPadre result) {
                                    Toast.makeText(DetailActivity.this, "Se agrego la categoria " + result.getName() + " a favoritos", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                }
            });
        } else {
            Toast.makeText(this, "Para agregar a favoritos, inicia sesion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClickButtonUbicacionDetailIndividualFragment(Articulo articulo) {
        Intent detailAMaps = new Intent(this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("articulo", articulo);
        detailAMaps.putExtras(bundle);
        startActivity(detailAMaps);
    }
}
