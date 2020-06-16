package com.example.dh_mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoriaPadre implements Serializable {

    private String id;
    private String name;
    @SerializedName("path_from_root")
    private List<CategoriaHijo> pathFromRoot;

    public CategoriaPadre(String id, String name, List<CategoriaHijo> pathFromRoot) {
        this.id = id;
        this.name = name;
        this.pathFromRoot = pathFromRoot;
    }

    public CategoriaPadre() {
    }

    public List<CategoriaHijo> getPathFromRoot() {
        return pathFromRoot;
    }

    public void setPathFromRoot(List<CategoriaHijo> pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
