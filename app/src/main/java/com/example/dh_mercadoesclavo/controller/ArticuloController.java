package com.example.dh_mercadoesclavo.controller;

import com.example.dh_mercadoesclavo.dao.ArticuloDao;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.util.ResultListener;

public class ArticuloController {

    private ArticuloDao articuloDao;

    public ArticuloController() {
        this.articuloDao = new ArticuloDao();
    }

    //este tiene un listener de la vista
    public void getFender(final ResultListener<ArticuloContainer> resultListenerDeLaView){
        this.articuloDao.getFender(new ResultListener<ArticuloContainer>() {
            @Override
            public void onFinish(ArticuloContainer result) {
                resultListenerDeLaView.onFinish(result);
            }
        });
    }

    public void getItemsPorId(String id, final ResultListener<Articulo> resultListenerDeLaView){
        this.articuloDao.getItemsPorId(id, new ResultListener<Articulo>() {
            @Override
            public void onFinish(Articulo result) {
                resultListenerDeLaView.onFinish(result);
            }
        });
    }
}
