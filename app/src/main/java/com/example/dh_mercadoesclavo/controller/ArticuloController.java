package com.example.dh_mercadoesclavo.controller;

import com.example.dh_mercadoesclavo.dao.ArticuloApiDao;
import com.example.dh_mercadoesclavo.dao.ArticuloFirestoreDao;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ArticuloController {

    private ArticuloApiDao articuloApiDao;
    private ArticuloFirestoreDao articuloFirestoreDao;

    public ArticuloController() {
        this.articuloApiDao = new ArticuloApiDao();
        this.articuloFirestoreDao = new ArticuloFirestoreDao();
    }

    //este tiene un listener de la vista
    public void getFender(final ResultListener<ArticuloContainer> resultListenerDeLaView){
        this.articuloApiDao.getFender(new ResultListener<ArticuloContainer>() {
            @Override
            public void onFinish(ArticuloContainer result) {
                resultListenerDeLaView.onFinish(result);
            }
        });
    }

    public void getItemsPorId(String id, final ResultListener<Articulo> resultListenerDeLaView){
        this.articuloApiDao.getItemsPorId(id, new ResultListener<Articulo>() {
            @Override
            public void onFinish(Articulo result) {
                resultListenerDeLaView.onFinish(result);
            }
        });
    }

    public void agregarArticuloAFirebase(final Articulo articulo, FirebaseUser usuario, final ResultListener<Articulo> listener){
        articuloFirestoreDao.agregarArticuloAFirebase(articulo, usuario, new ResultListener<Articulo>() {
            @Override
            public void onFinish(Articulo result) {
                listener.onFinish(result);
            }
        });
    }

    public void consultarArticulosEnFirebase(FirebaseUser usuario, final ResultListener<List<Articulo>> listener){
        articuloFirestoreDao.consultarArticulosEnFirebase(usuario, new ResultListener<List<Articulo>>() {
            @Override
            public void onFinish(List<Articulo> result) {
                listener.onFinish(result);
            }
        });
    }
}
