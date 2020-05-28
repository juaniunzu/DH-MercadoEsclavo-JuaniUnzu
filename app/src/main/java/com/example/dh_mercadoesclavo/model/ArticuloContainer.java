package com.example.dh_mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticuloContainer {

    @SerializedName("site_id")
    private String siteId;
    private String query;
    private List<Articulo> results;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Articulo> getResults() {
        return results;
    }

    public void setResults(List<Articulo> results) {
        this.results = results;
    }
}
