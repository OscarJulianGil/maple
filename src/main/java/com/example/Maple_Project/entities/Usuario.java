package com.example.Maple_Project.entities;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "codEmpresa")
    private Empresa empresa;

    //private String rol;

    //boolean admin;
    //boolean operativo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    /*public String getRol() {
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
        }*/
}