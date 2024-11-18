package com.example.appgestion;

public class PrendaRopa {
    private String tipo;
    private String talla;
    private String color;
    private double precio;

    public PrendaRopa() {
    }

    public PrendaRopa(String tipo, String talla, String color, double precio) {
        this.tipo = tipo;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "PrendaRopa{" +
                "tipo='" + tipo + '\'' +
                ", talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                '}';
    }
}

