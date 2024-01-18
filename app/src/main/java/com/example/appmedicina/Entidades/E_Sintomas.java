package com.example.appmedicina.Entidades;

public class E_Sintomas {

    private int id;
    private String nombreSintoma;
    private String fechaSintoma;
    private String horaSintoma;
    private String intensidadSintoma;
    private String notaSintoma;
    private byte[] imageSintoma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreSintoma() {
        return nombreSintoma;
    }

    public void setNombreSintoma(String nombreSintoma) {
        this.nombreSintoma = nombreSintoma;
    }

    public String getFechaSintoma() {
        return fechaSintoma;
    }

    public void setFechaSintoma(String fechaSintoma) {
        this.fechaSintoma = fechaSintoma;
    }

    public String getHoraSintoma() {
        return horaSintoma;
    }

    public void setHoraSintoma(String horaSintoma) {
        this.horaSintoma = horaSintoma;
    }

    public String getIntensidadSintoma() {
        return intensidadSintoma;
    }

    public void setIntensidadSintoma(String intensidadSintoma) {
        this.intensidadSintoma = intensidadSintoma;
    }

    public String getNotaSintoma() {
        return notaSintoma;
    }

    public void setNotaSintoma(String notaSintoma) {
        this.notaSintoma = notaSintoma;
    }

    public byte[] getImageSintoma() {
        return imageSintoma;
    }

    public void setImageSintoma(byte[] imageSintoma) {
        this.imageSintoma = imageSintoma;
    }
}