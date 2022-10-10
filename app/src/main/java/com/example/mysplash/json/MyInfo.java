package com.example.mysplash.json;

import java.io.Serializable;

public class MyInfo implements Serializable {
    private String usuario;
    private String password;
    private String correo;
    private Boolean sexo;
    public MyInfo() {
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Boolean getSexo() {
        return sexo;
    }
    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

}