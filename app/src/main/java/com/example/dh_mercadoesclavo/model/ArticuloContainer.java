package com.example.dh_mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArticuloContainer implements Serializable {

    @SerializedName("site_id")
    private String siteId;
    private String query;
    private List<Articulo> results;
    private List<ArticuloContainerFilters> filters;
    private Paging paging;

    public ArticuloContainer(String siteId, String query, List<Articulo> results, List<ArticuloContainerFilters> filters, Paging paging) {
        this.siteId = siteId;
        this.query = query;
        this.results = results;
        this.filters = filters;
        this.paging = paging;
    }

    public ArticuloContainer() {
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<ArticuloContainerFilters> getFilters() {
        return filters;
    }

    public void setFilters(List<ArticuloContainerFilters> filters) {
        this.filters = filters;
    }

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
