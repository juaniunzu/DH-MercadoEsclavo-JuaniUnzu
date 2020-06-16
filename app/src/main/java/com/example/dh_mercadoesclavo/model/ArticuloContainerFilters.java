package com.example.dh_mercadoesclavo.model;

import java.io.Serializable;
import java.util.List;

public class ArticuloContainerFilters implements Serializable {

    private List<ArticuloFilters> values;

    public ArticuloContainerFilters(List<ArticuloFilters> values) {
        this.values = values;
    }

    public ArticuloContainerFilters() {
    }

    public List<ArticuloFilters> getValues() {
        return values;
    }

    public void setValues(List<ArticuloFilters> values) {
        this.values = values;
    }
}
