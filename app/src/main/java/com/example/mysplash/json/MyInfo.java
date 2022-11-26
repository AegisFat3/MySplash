package com.example.mysplash.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyInfo implements Serializable {
    private String user;
    private String correo;
    private String contrasena;
    private String numero;
    private String fecha;
    private String edad;
    private String[] escuela;
    private Boolean gen;
    private Boolean notifi;
    private Boolean feliz;

    public List<MyData> getContras() {
        return contras;
    }

    public void setContras(List<MyData> contras) {
        this.contras = contras;
    }

    private List<MyData> contras= new ArrayList<>();


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String[] getEscuela() {
        return escuela;
    }

    public void setEscuela(String[] escuela) {
        this.escuela = escuela;
    }

    public Boolean getGen() {
        return gen;
    }

    public void setGen(Boolean gen) {
        this.gen = gen;
    }

    public Boolean getNotifi() {
        return notifi;
    }

    public void setNotifi(Boolean notifi) {
        this.notifi = notifi;
    }

    public Boolean getFeliz() {
        return feliz;
    }

    public void setFeliz(Boolean feliz) {
        this.feliz = feliz;
    }
}