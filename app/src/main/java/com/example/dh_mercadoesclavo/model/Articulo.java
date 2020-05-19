package com.example.dh_mercadoesclavo.model;

import com.example.dh_mercadoesclavo.R;

import java.io.Serializable;

//POJO - objeto a representar
public class Articulo implements Serializable {

    private String nombre;
    private String precio;
    private Integer imagen;
    private String descripcion;
    private Boolean disponibleParaEnvios;

    public Articulo(String nombre, String precio, Integer imagen, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public Articulo(String nombre, String precio, Integer imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Articulo(String nombre, String precio, Integer imagen, String descripcion, Boolean disponibleParaEnvios) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.disponibleParaEnvios = disponibleParaEnvios;
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

    public String disponibleParaEnviar(){
        if(this.disponibleParaEnvios == null){
            return "Consultar por envio";
        } else if (this.disponibleParaEnvios){
            return "Disponible para enviar";
        } else {
            return "No disponible para enviar";
        }
    }

    public Integer campoEnvioStyle(){
        if(this.disponibleParaEnvios == null){
            return R.style.Subtitle1NotClickable;
        } else if (this.disponibleParaEnvios){
            return R.style.Subtitle1Clickable;
        } else {
            return R.style.Subtitle1NotClickable;
        }
    }
}
