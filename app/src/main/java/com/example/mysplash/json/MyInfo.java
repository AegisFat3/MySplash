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

    public String getBox1() {
        return box1;
    }

    public void setBox1(String box1) {
        this.box1 = box1;
    }

    public String getBox2() {
        return box2;
    }

    public void setBox2(String box2) {
        this.box2 = box2;
    }

    private String box1;
    private String box2;
    private int gen;
    private int notifi;
    private int feliz;

    public int getId_usr() {
        return id_usr;
    }

    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }

    private int id_usr;

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


    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public int getNotifi() {
        return notifi;
    }

    public void setNotifi(int notifi) {
        this.notifi = notifi;
    }

    public int getFeliz() {
        return feliz;
    }

    public void setFeliz(int feliz) {
        this.feliz = feliz;
    }
}