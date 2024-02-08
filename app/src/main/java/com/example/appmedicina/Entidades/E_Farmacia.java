package com.example.appmedicina.Entidades;

public class E_Farmacia {
    private String nombre;
    private String direccion;
    private String businessStatus;
    private boolean isOpen;
    private double rating;

    public E_Farmacia(String nombre, String direccion, String businessStatus, boolean isOpen, double rating) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.businessStatus = businessStatus;
        this.isOpen = isOpen;
        this.rating = rating;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public double getRating() {
        return rating;
    }

}
