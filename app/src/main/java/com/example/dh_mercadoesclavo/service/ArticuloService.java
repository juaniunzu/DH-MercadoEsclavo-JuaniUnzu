package com.example.dh_mercadoesclavo.service;


import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.model.CategoriaPadre;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * interfaz con cada uno de los endpoints que voy a usar (cada servicio q la api tiene)
 */
public interface ArticuloService {

    //https://api.mercadolibre.com/sites/MLA/search?q=fender jazz bass&category=MLA1182
    //me traigo busqueda fender jazz bass en categoria instrumentos musicales
    @GET("sites/MLA/search?q=fender jazz bass&category=MLA1182&limit=10")
    Call<ArticuloContainer> getFender();

    @GET("items/{id}")
    Call<Articulo> getItemsPorId(@Path("id") String id);

    @GET("sites/MLA/search")
    Call<ArticuloContainer> getItemsPorQuery(
            @Query("q") String searchText,
            @Query("limit") Integer limit,
            @Query("category") String categoria);

    @GET("categories/{id}")
    Call<CategoriaPadre> getCategoriasPorId(@Path("id") String id);


}
