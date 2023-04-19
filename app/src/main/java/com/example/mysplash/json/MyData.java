package com.example.mysplash.json;

import java.io.Serializable;

public class MyData implements Serializable {
    private String contra;

    public byte[] getData() {
        return data;
    }
    private String latitud;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    private String longitud;

    public void setData(byte[] data) {
        this.data = data;
    }

    private byte[] data;
    private int image;
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

    public MyData() {

    }

    public int getId_contra() {
        return id_contra;
    }

    public void setId_contra(int id_contra) {
        this.id_contra = id_contra;
    }

    private int id_contra;
    private String usuario;
    private int id_usr;


    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId_usr() {
        return id_usr;
    }

    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }



}