package com.example.dh_mercadoesclavo.controller;

import com.example.dh_mercadoesclavo.dao.ArticuloApiDao;
import com.example.dh_mercadoesclavo.dao.ArticuloFirestoreDao;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.ArticuloContainer;
import com.example.dh_mercadoesclavo.model.CategoriaPadre;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ArticuloController {

    private ArticuloApiDao articuloApiDao;
    private ArticuloFirestoreDao articuloFirestoreDao;
    private Integer offset = 0;
    private static final Integer PAGE_SIZE = 10;
    private Boolean hayMasResultados = true;

    public ArticuloController() {
        this.articuloApiDao = new ArticuloApiDao();
        this.articuloFirestoreDao = new ArticuloFirestoreDao();
    }

    public void getCategoriasPorId(String id, final ResultListener<CategoriaPadre> listener){
        this.articuloApiDao.getCategoriasPorId(id, new ResultListener<CategoriaPadre>() {
            @Override
            public void onFinish(CategoriaPadre result) {
                listener.onFinish(result);
            }
        });
    }

    public void getItemsPorQuery(String searchText, Integer pageSize, Integer offset, String price, final ResultListener<ArticuloContainer> listener){
        this.articuloApiDao.getItemsPorQuery(searchText, pageSize, offset, price, new ResultListener<ArticuloContainer>() {
            @Override
            public void onFinish(ArticuloContainer result) {
                listener.onFinish(result);
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

    public void agregarCategoriaFavoritaAFirebase(CategoriaPadre categoriaPadre, FirebaseUser usuarioLogueado, final ResultListener<CategoriaPadre> listener){
        articuloFirestoreDao.agregarCategoriaFavoritaAFirebase(categoriaPadre, usuarioLogueado, new ResultListener<CategoriaPadre>() {
            @Override
            public void onFinish(CategoriaPadre result) {
                listener.onFinish(result);
            }
        });
    }

    public void consultarCategoriasEnFirebase(FirebaseUser usuarioLogueado, final ResultListener<List<CategoriaPadre>> listener){
        articuloFirestoreDao.consultarCategoriasEnFirebase(usuarioLogueado, new ResultListener<List<CategoriaPadre>>() {
            @Override
            public void onFinish(List<CategoriaPadre> result) {
                listener.onFinish(result);
            }
        });
    }
}
