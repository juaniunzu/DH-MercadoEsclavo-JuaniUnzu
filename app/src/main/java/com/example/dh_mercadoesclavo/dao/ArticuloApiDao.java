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
public class ArticuloApiDao extends RetrofitDao{

    private ArticuloService articuloService;

    public ArticuloApiDao() {

        //esta linea es la que hace la implementacion de la interfaz nunca usada ArticuloService
        //y se encargara de sobreescribir los metodos de los servicios ofrecidos, que estan detallados
        //en la interfaz. el atributo super.retrofit viene heredado del padre (protected en clase RetrofitDao)
        this.articuloService = super.retrofit.create(ArticuloService.class);
    }

    public void getItemsPorId(String id, final ResultListener<Articulo> resultListenerDelController){
        Call<Articulo> call = this.articuloService.getItemsPorId(id);
        call.enqueue(new Callback<Articulo>() {
            @Override
            public void onResponse(Call<Articulo> call, Response<Articulo> response) {
                if(response.isSuccessful()) {
                    Articulo articulo = response.body();
                    resultListenerDelController.onFinish(articulo);
                } else{
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<Articulo> call, Throwable t) {
                t.printStackTrace();
            }
        });
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








}
