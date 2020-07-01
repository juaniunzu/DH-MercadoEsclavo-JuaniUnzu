package com.example.dh_mercadoesclavo.model;


import com.example.dh_mercadoesclavo.R;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//POJO - objeto a representar
public class Articulo implements Serializable {

    private String id;
    private String title;
    private Double price;
    private String condition;
    @SerializedName("thumbnail")
    private String foto;
    private String nombre;
    private String precio;
    private Integer imagen;
    private String descripcion;
    private Boolean disponibleParaEnvios;
    private List<Pic> pictures;
    @SerializedName("seller_address")
    private ItemSellerAddress sellerAddress;
    private Geolocation geolocation;
    @SerializedName("category_id")
    private String categoryId;

    public Articulo() {
    }

    public Articulo(String id, String title, Double price, String condition, String foto, String nombre, String precio, Integer imagen, String descripcion, Boolean disponibleParaEnvios, List<Pic> pictures, ItemSellerAddress sellerAddress, Geolocation geolocation, String categoryId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.condition = condition;
        this.foto = foto;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.disponibleParaEnvios = disponibleParaEnvios;
        this.pictures = pictures;
        this.sellerAddress = sellerAddress;
        this.geolocation = geolocation;
        this.categoryId = categoryId;
    }

    //usado en lista hardcodeada
    public Articulo(String nombre, String precio, Integer imagen, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    //usado en lista hardcodeada
    public Articulo(String nombre, String precio, Integer imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    //usado en lista hardcodeada
    public Articulo(String nombre, String precio, Integer imagen, String descripcion, Boolean disponibleParaEnvios) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.disponibleParaEnvios = disponibleParaEnvios;
    }

    public ItemSellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(ItemSellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public List<Pic> getPictures() {
        return pictures;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public void setPictures(List<Pic> pictures) {
        this.pictures = pictures;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String disponibleParaEnviar() {
        if (this.disponibleParaEnvios == null) {
            return "Consultar por envio";
        } else if (this.disponibleParaEnvios) {
            return "Disponible para enviar";
        } else {
            return "No disponible para enviar";
        }
    }

    public Integer campoEnvioStyle() {
        if (this.disponibleParaEnvios == null) {
            return R.style.Subtitle1NotClickable;
        } else if (this.disponibleParaEnvios) {
            return R.style.Subtitle1Clickable;
        } else {
            return R.style.Subtitle1NotClickable;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getDisponibleParaEnvios() {
        return disponibleParaEnvios;
    }

    public void setDisponibleParaEnvios(Boolean disponibleParaEnvios) {
        this.disponibleParaEnvios = disponibleParaEnvios;
    }
}
