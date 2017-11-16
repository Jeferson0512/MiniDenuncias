package com.tecsup.jeferson.minidenuncias.Model;

/**
 * Created by Alumno on 16/11/2017.
 */

public class Usuario {

    public long id;
    public String username;
    public String password;
    public String nombres;
    public String correo;
    public String imagen;
    public long estado;

    public Usuario() {
    }

    public Usuario(long id, String username, String password, String nombres, String correo, String imagen, long estado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombres = nombres;
        this.correo = correo;
        this.imagen = imagen;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombres='" + nombres + '\'' +
                ", correo='" + correo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado=" + estado +
                '}';
    }
}
