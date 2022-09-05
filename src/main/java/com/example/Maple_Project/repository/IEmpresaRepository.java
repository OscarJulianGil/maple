package com.example.Maple_Project.repository;

import com.example.Maple_Project.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query("SELECT t FROM Empresa t WHERE t.nit = ?1")
    ArrayList<Empresa> findByNit(int nit);

}

