package com.example.dh_mercadoesclavo.service;


import com.example.dh_mercadoesclavo.model.ArticuloContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * interfaz con cada uno de los endpoints que voy a usar (cada servicio q la api tiene)
 */
public interface ArticuloService {

    //https://api.mercadolibre.com/sites/MLA/search?q=fender jazz bass&category=MLA1182
    //me traigo busqueda fender jazz bass en categoria instrumentos musicales
    @GET("search?q=fender jazz bass&category=MLA1182")
    Call<ArticuloContainer> getFender();




}
