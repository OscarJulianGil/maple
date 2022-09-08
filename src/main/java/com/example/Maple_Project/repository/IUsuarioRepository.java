package com.example.Maple_Project.repository;

import com.example.Maple_Project.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

}