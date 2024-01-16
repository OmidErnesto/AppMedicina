package com.example.appmedicina.Entidades;

public class E_Medicamentos {

    private int id;
    private String nombreMedicamento;
    private String fechaMedicamento;
    private String horaMedicamento;
    private double cantidadMedicamento;
    private String notaMedicamento;
    private byte[] imageMedicamento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getFechaMedicamento() {
        return fechaMedicamento;
    }

    public void setFechaMedicamento(String fechaMedicamento) {
        this.fechaMedicamento = fechaMedicamento;
    }

    public String getHoraMedicamento() {
        return horaMedicamento;
    }

    public void setHoraMedicamento(String horaMedicamento) {
        this.horaMedicamento = horaMedicamento;
    }

    public double getCantidadMedicamento() {
        return cantidadMedicamento;
    }

    public void setCantidadMedicamento(double cantidadMedicamento) {
        this.cantidadMedicamento = cantidadMedicamento;
    }

    public String getNotaMedicamento() {
        return notaMedicamento;
    }

    public void setNotaMedicamento(String notaMedicamento) {
        this.notaMedicamento = notaMedicamento;
    }

    public byte[] getImageMedicamento() {
        return imageMedicamento;
    }

    public void setImageMedicamento(byte[] imageMedicamento) {
        this.imageMedicamento = imageMedicamento;
    }
}
