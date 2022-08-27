package com.example.Maple_Project.repository;

import com.example.Maple_Project.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {
}

