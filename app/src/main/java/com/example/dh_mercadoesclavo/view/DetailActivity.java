package com.example.dh_mercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailFragment.DetailFragmentListener {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewPager = findViewById(R.id.activityDetailViewPager);

        toolbar = findViewById(R.id.activityDetailToolBar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        Intent desdeMain = getIntent();
        Bundle datosDesdeMain = desdeMain.getExtras();

        Articulo articuloClickeado = (Articulo) datosDesdeMain.getSerializable("articulo");
        ArrayList<Articulo> articuloArrayList = (ArrayList<Articulo>) datosDesdeMain.getSerializable("lista");
        List<Fragment> listaFragmentsViewPager = generarFragments(articuloArrayList);

        Integer indice = articuloArrayList.indexOf(articuloClickeado);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listaFragmentsViewPager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(indice);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();




    }

    @Override
    public void onClickButtonFavoritosDetailFragment(Articulo articulo) {
        ArticuloController articuloController = new ArticuloController();
        articuloController.agregarArticuloAFirebase(articulo, currentUser, new ResultListener<Articulo>() {
            @Override
            public void onFinish(Articulo result) {
                Toast.makeText(DetailActivity.this, "Se agregó el artículo a favoritos", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    private List<Fragment> generarFragments(List<Articulo> articuloList){
        List<Fragment> listaADevolver = new ArrayList<>();
        for (Articulo articulo : articuloList) {
            Fragment fragment = DetailFragment.crearDetailFragment(articulo, this);
            listaADevolver.add(fragment);
        }
        return listaADevolver;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }

        return true;
    }


}
