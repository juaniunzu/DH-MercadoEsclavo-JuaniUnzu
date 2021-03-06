package com.example.dh_mercadoesclavo.model;

import java.io.Serializable;

public class ArticuloFilters implements Serializable {

    private String id;
    private String name;

    public ArticuloFilters(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArticuloFilters() {
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
