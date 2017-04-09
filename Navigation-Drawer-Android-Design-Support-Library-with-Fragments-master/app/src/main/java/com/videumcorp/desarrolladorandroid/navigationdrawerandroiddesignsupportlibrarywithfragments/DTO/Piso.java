package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO;

/**
 * Created by Javier on 20/03/2017.
 */

public class Piso
{
    private String localizacion;
    private String opcion;
    private String vivienda;
    private String descripcion;
    private int precio;
    private String nombre;
    private int habitaciones;
    private int banyos;
    private int tam;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    private String imagen;

    public String getLocalizacion() {
        return localizacion;
    }

    public Piso(String localizacion, String opcion, String vivienda, int precio, String nombre,
                int habitaciones, int banyos, int tam, String imagen, String descripcion) {
        this.localizacion = localizacion;
        this.opcion = opcion;
        this.vivienda = vivienda;
        this.precio = precio;
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.banyos = banyos;
        this.tam = tam;
        this.imagen=imagen;
        this.descripcion=descripcion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getVivienda() {
        return vivienda;
    }

    public void setVivienda(String vivienda) {
        this.vivienda = vivienda;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getBanyos() {
        return banyos;
    }

    public void setBanyos(int banyos) {
        this.banyos = banyos;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }
}
