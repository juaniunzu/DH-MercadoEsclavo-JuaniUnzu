package com.example.dh_mercadoesclavo.model;

public class Paging {

    private int total;
    private int offset;
    private int limit;

    public Paging(int total, int offset, int limit) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
    }

    public Paging() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
