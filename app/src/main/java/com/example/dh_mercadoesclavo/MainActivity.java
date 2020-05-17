package com.example.dh_mercadoesclavo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ArticuloFragment.ArticuloFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creo fragment y lo pego en el main
        ArticuloFragment articuloFragment = new ArticuloFragment();
        pegarFragment(articuloFragment);

    }

    //metodo que pega fragment en el main
    private void pegarFragment(Fragment unFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainFragmentContainer, unFragment).addToBackStack("add");
        fragmentTransaction.commit();
    }

    @Override
    public void onClickArticuloFragment(Articulo unArticulo) {
        Bundle mainADetalle = new Bundle();
        mainADetalle.putSerializable("articulo", unArticulo);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(mainADetalle);
        pegarFragment(fragment);
    }
}
