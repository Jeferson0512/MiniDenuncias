package com.tecsup.jeferson.minidenuncias.Model;

/**
 * Created by Usuario on 13/11/2017.
 */

public class Registro {

    private Integer id;
    private String titulo;
    private String imagen;
    private String descripcion;
    private Float lat;
    private Float lng;
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", estado='" + estado + '\'' +
                '}';
    }
}
