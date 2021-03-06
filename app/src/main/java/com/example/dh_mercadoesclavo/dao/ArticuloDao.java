package com.example.dh_mercadoesclavo.dao;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;

import java.util.ArrayList;
import java.util.List;

public class ArticuloDao {

    /**
     * metodo que devuelve una lista fija de articulos
     * @return
     */
    public static List<Articulo> getArticulos(){
        List<Articulo> listaADevolver = new ArrayList<>();

        listaADevolver.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos, "Juego de adornos navideños surtidos, consultar disponibilidad antes de ofertar.", true));
        listaADevolver.add(new Articulo("Lampara", "$2.500", R.drawable.lampara));
        listaADevolver.add(new Articulo("Taladro", "$3.800", R.drawable.taladro, "Taladro industrial, somos importadores de toda la gama, consultar por otros artículos", true));
        listaADevolver.add(new Articulo("Reproductor de vinilos", "$5.600", R.drawable.tocadiscos));
        listaADevolver.add(new Articulo("Juego de vajilla", "$3.200", R.drawable.vajilla, "Juego de vajilla china importada, varios juegos disponibles, consultá por envios dentro de caba!", false));
        listaADevolver.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos));
        listaADevolver.add(new Articulo("Lampara", "$2.500", R.drawable.lampara, "Lampara tipo Ikea, distintos colores disponibles, consultar por envíos al interior"));
        listaADevolver.add(new Articulo("Taladro", "$3.800", R.drawable.taladro, "Taladro industrial, somos importadores de toda la gama, consultar por otros artículos", false));
        listaADevolver.add(new Articulo("Reproductor de vinilos", "$5.600", R.drawable.tocadiscos, "Tope de gama, somos representantes de la marca, consultá por la linea completa de audio profesional", true));
        listaADevolver.add(new Articulo("Juego de vajilla", "$3.200", R.drawable.vajilla, "Juego de vajilla china importada, varios juegos disponibles, consultá por envios dentro de caba!", true));
        listaADevolver.add(new Articulo("Juego de adornos de navidad", "$1.000", R.drawable.adornosnavidenos, "Juego de adornos navideños surtidos, consultar disponibilidad antes de ofertar."));
        listaADevolver.add(new Articulo("Lampara", "$2.500", R.drawable.lampara));
        listaADevolver.add(new Articulo("Taladro", "$3.800", R.drawable.taladro, "Taladro industrial, somos importadores de toda la gama, consultar por otros artículos", true));
        listaADevolver.add(new Articulo("Reproductor de vinilos", "$5.600", R.drawable.tocadiscos, "Tope de gama, somos representantes de la marca, consultá por la linea completa de audio profesional"));
        listaADevolver.add(new Articulo("Juego de vajilla", "$3.200", R.drawable.vajilla));
        return listaADevolver;
    }
}
