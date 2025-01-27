package com.example.hito7;

public class PrendaRopa {
    private String nombre;
    private String talla;
    private Estilos estilo; // Cambiado a enum Estilos
    private double precio;
    private int imagen; // ID de la imagen (en drawable)
    private String descripcion;
    private String imagenUri; // URI de la imagen

    public PrendaRopa() {
    }

    public PrendaRopa(String nombre, String talla, Estilos estilo, double precio, int imagen) {
        this.nombre = nombre;
        this.talla = talla;
        this.estilo = estilo;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Estilos getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilos estilo) {
        this.estilo = estilo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getImagenUri() {
        return imagenUri;
    }

    public void setImagenUri(String imagenUri) {
        this.imagenUri = imagenUri;
    }

    public String getDescripcion() {
        return "Nombre: " + this.getNombre()
                + "\nTalla: " + this.getTalla()
                + "\nEstilo: " + this.getEstilo()
                + "\nPrecio: " + this.getPrecio();
    }
}
