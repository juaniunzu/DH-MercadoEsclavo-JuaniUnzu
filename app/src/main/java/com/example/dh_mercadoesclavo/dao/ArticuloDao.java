package com.example.dh_mercadoesclavo.dao;


import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.service.ArticuloService;
import com.example.dh_mercadoesclavo.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//DAO: DATA ACCESS OBJECT -  la clase que uso para obtener datos

//se crea esta clase solo para que tenga los metodos de devolver listas de articulos. Es buena practica porque mantengo el fragment
//mas ordenado, con menos codigo, y ademas, al momento en que la lista pueda ser traida de otra fuente, directamente
//se toca este modulo.
public class ArticuloDao extends RetrofitDao{

    private ArticuloService articuloService;

    public ArticuloDao() {

        //esta linea es la que hace la implementacion de la interfaz nunca usada ArticuloService
        //y se encargara de sobreescribir los metodos de los servicios ofrecidos, que estan detallados
        //en la interfaz. el atributo super.retrofit viene heredado del padre (protected en clase RetrofitDao)
        this.articuloService = super.retrofit.create(ArticuloService.class);
    }

    //tiene un listener del controller
    public void getFender(final ResultListener<ArticuloContainer> resultListenerDelController){

        //creo la llamada
        Call<ArticuloContainer> call = this.articuloService.getFender();
        //la pongo en cola y le paso un new callback (el escuchador de la respuesta)
        call.enqueue(new Callback<ArticuloContainer>() {

            @Override
            //la llamada se ejecuto y hubo respuesta (no asegura que sea respuesta OK, orden 2xx, puede devolver
            //4xx o 5xx y hay que manejar ese error)
            public void onResponse(Call<ArticuloContainer> call, Response<ArticuloContainer> response) {
                if(response.isSuccessful()){
                    ArticuloContainer body = response.body();
                    resultListenerDelController.onFinish(body);
                } else {
                    response.errorBody();
                    //manejar error, puede ser tipo 4xx o 5xx
                }
            }

            @Override
            //la llamada directamente no se ejecuto (error grave)
            public void onFailure(Call<ArticuloContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }




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
