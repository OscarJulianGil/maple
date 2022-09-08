package com.example.Maple_Project.entities;

import javax.persistence.*;

@Entity
@Table (name = "Empresa")

public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "nombre")
    private String nombre;
    @Column (name = "documento")
    private String documento;
    @Column (name = "direccion")
    private String direccion;
    @Column (name = "telefono")
    private int telefono;
    @Column (name = "nit")
    private int nit;

    @ManyToOne
    @JoinColumn(name = "usuarios")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "emovimientos")
    private MovimientoDinero movimientoDinero;

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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MovimientoDinero getMovimientoDinero() {
        return movimientoDinero;
    }

    public void setMovimientoDinero(MovimientoDinero movimientoDinero) {
        this.movimientoDinero = movimientoDinero;
    }
}