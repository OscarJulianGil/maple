package com.example.mapleproject.repository;

import com.example.mapleproject.entities.Empresa;
import com.example.mapleproject.entities.MovimientoDinero;
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


    //Metodo para ver la suma de TODOS LOS MOVIMIENTOS
    @Query("SELECT SUM(monto) from MovimientoDinero")
    public abstract Long SumarMonto();

    //Metodo para ver la suma de los montos por empleado
    @Query("SELECT SUM(monto) from MovimientoDinero where umovimientos=?1")
    public abstract Long MontosPorEmpleado(Integer id); //id del empleado

    //Metodo para ver la suma de los movimientos por empresa
    @Query("select sum(monto) from MovimientoDinero where umovimientos in (select id from Usuario where codEmpresa= ?1)")
    public abstract Long MontosPorEmpresa(Integer id); //Id de la empresa

    //Metodo que me trae el id de un usuario cuando tengo su correo
    @Query("select id from Usuario where correo=?1")
    public abstract Integer IdPorCorreo(String correo);

}
