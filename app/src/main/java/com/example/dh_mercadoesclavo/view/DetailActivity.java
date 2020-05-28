package com.example.dh_mercadoesclavo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewPager = findViewById(R.id.activityDetailViewPager);

        Intent desdeMain = getIntent();
        Bundle datosDesdeMain = desdeMain.getExtras();

        Articulo articuloClickeado = (Articulo) datosDesdeMain.getSerializable("articulo");
        ArrayList<Articulo> articuloArrayList = (ArrayList<Articulo>) datosDesdeMain.getSerializable("lista");
        List<Fragment> listaFragmentsViewPager = generarFragments(articuloArrayList);

        Integer indice = articuloArrayList.indexOf(articuloClickeado);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listaFragmentsViewPager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(indice);

    }

    private List<Fragment> generarFragments(List<Articulo> articuloList){
        List<Fragment> listaADevolver = new ArrayList<>();
        for (Articulo articulo : articuloList) {
            Fragment fragment = DetailFragment.crearDetailFragment(articulo);
            listaADevolver.add(fragment);
        }
        return listaADevolver;
    }
}
