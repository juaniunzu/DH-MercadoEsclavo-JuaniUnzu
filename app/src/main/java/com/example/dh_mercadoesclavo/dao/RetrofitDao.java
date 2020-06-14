package com.example.dh_mercadoesclavo.dao;

import com.example.dh_mercadoesclavo.service.ArticuloService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitDao {

    private static final String BASE_URL = "https://api.mercadolibre.com/";
    protected Retrofit retrofit;

    public RetrofitDao() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
