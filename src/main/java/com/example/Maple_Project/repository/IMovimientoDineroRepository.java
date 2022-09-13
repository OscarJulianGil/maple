package com.example.Maple_Project.repository;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IMovimientoDineroRepository extends JpaRepository<MovimientoDinero, Integer> {

    List<MovimientoDinero> findByEmpresaId(int empresaId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("DELETE FROM MovimientoDinero md WHERE md.empresa = :empresa")
    void deleteMovimientoDineroByEmpresa (Empresa empresa);
}
