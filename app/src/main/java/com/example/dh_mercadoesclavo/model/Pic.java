package com.example.dh_mercadoesclavo.model;

import java.io.Serializable;

public class Pic implements Serializable {

    private String id;
    private String url;

    public Pic(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public Pic() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
