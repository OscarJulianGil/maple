package com.example.Maple_Project.repository;

import com.example.Maple_Project.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoDineroRepository extends JpaRepository<MovimientoDinero, Integer> {

}
