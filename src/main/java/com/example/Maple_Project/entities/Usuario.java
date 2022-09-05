package com.example.Maple_Project.entities;

public class Usuario {

    private String nombre;
    private String correo;
    Empresa empresa;
    private String rol;

    boolean admin;
    boolean operativo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if (rol == "administrador"){
            this.admin = true;
            this.operativo = false;
        }
        else{
            this.admin = false;
            this.operativo = true;
        }
    }
}

