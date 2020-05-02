package com.example.dh_mercadoesclavo;


import java.util.ArrayList;
import java.util.List;

//se crea esta clase solo para que tenga el metodo de devolver lista de articulos. Se pone abstracta porque su funcion
//solo es tener este metodo, y no nos interesa que se pueda instanciar. Es buena practica porque mantengo el fragment
//mas ordenado, con menos codigo, y ademas, al momento en que la lista pueda ser traida de otra fuente, directamente
//se toca este modulo.
public abstract class ProveedorDeArticulos {

    //static para que pueda llamarse sin ser instanciada la clase
    public static List<Articulo> getArticulos(){
        List<Articulo> listaADevolver = new ArrayList<>();

        listaADevolver.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos));
        listaADevolver.add(new Articulo("Lampara", "$2.500", R.drawable.lampara));
        listaADevolver.add(new Articulo("Taladro", "$3.800", R.drawable.taladro));
        listaADevolver.add(new Articulo("Reproductor de vinilos", "5.600", R.drawable.tocadiscos));
        listaADevolver.add(new Articulo("Juego de vajilla", "$3.200", R.drawable.vajilla));
        listaADevolver.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos));
        listaADevolver.add(new Articulo("Lampara", "$2.500", R.drawable.lampara));
        listaADevolver.add(new Articulo("Taladro", "$3.800", R.drawable.taladro));
        listaADevolver.add(new Articulo("Reproductor de vinilos", "5.600", R.drawable.tocadiscos));
        listaADevolver.add(new Articulo("Juego de vajilla", "$3.200", R.drawable.vajilla));
        listaADevolver.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos));
        listaADevolver.add(new Articulo("Lampara", "$2.500", R.drawable.lampara));
        listaADevolver.add(new Articulo("Taladro", "$3.800", R.drawable.taladro));
        listaADevolver.add(new Articulo("Reproductor de vinilos", "5.600", R.drawable.tocadiscos));
        listaADevolver.add(new Articulo("Juego de vajilla", "$3.200", R.drawable.vajilla));

        return listaADevolver;
    }

}
