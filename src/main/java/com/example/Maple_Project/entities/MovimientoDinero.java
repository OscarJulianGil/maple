package com.example.Maple_Project.entities;

import javax.persistence.*;

@Entity
@Table(name = "MovimientoDinero")
public class MovimientoDinero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "monto")
    private int monto;
    @Column(name = "concepto")
    private String concepto;

    @ManyToOne
    @JoinColumn(name = "umovimientos")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "emovimientos")
    private Empresa empresa;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }




}
