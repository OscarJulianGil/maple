package com.example.mapleproject.repository;

import com.example.mapleproject.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1 and u.password = ?2")
    ArrayList<Usuario> validaCredenciales(String usuario,String password);

    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    ArrayList<Usuario> existeCorreo(String correo);

    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Usuario findByUsername(String correo);

}
