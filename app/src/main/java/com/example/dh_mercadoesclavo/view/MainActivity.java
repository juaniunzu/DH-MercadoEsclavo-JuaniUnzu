package com.example.dh_mercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.view.ArticuloFragment;
import com.example.dh_mercadoesclavo.view.DetailFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeFragment.ArticuloHomeFragmentListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout activityMainDrawerLayout;
    private NavigationView activityMainNavigationView;
    private Toolbar activityMainToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();

        activityMainToolBar = findViewById(R.id.activityMainToolBar);
        setSupportActionBar(activityMainToolBar);
        getSupportActionBar().setTitle("Mercado Esclavo");




        activityMainNavigationView.setNavigationItemSelectedListener(this);

        HomeFragment fragment = new HomeFragment();
        pegarFragment(fragment);

    }

    /**
     * metodo que linkea los atributos del main con los elementos xml correspondientes
     */
    private void findViewsById() {
        activityMainDrawerLayout = findViewById(R.id.activityMainDrawerLayout);
        activityMainNavigationView = findViewById(R.id.activityMainNavigationView);
    }

    /**
     * metodo que pega fragments en el main activity. Se le debe pasar como parametro el fragment deseado.
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

        switch (item.getItemId()){
            case R.id.navigationMenuAboutUs:
                pegarFragment(new AboutUsFragment());
                activityMainDrawerLayout.closeDrawers();
                break;
        }
        return false;
    }


    @Override
    public void onClickArticuloFragmentHome(Articulo unArticulo, List<Articulo> articuloList) {

        Intent mainADetail = new Intent(this, DetailActivity.class);
        Bundle mainADetalle = new Bundle();
        mainADetalle.putSerializable("articulo", unArticulo);
        mainADetalle.putSerializable("lista", (ArrayList) articuloList);
        mainADetail.putExtras(mainADetalle);
        startActivity(mainADetail);

    }
}
