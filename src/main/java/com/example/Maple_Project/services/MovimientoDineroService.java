package com.example.Maple_Project.services;

import com.example.Maple_Project.entities.MovimientoDinero;
import com.example.Maple_Project.repository.IMovimientoDineroRepository;

import java.util.ArrayList;
import java.util.Optional;

public class MovimientoDineroService {
    private IMovimientoDineroRepository movimientoDineroRepository;

    public MovimientoDineroService (IMovimientoDineroRepository repository) {
        this.movimientoDineroRepository = repository;
    }

    public ArrayList<MovimientoDinero> selectAll(){
        return (ArrayList<MovimientoDinero>) movimientoDineroRepository.findAll();
    }

    public Response crearMovimientoDinero (MovimientoDinero data, int id){
        Response response = new Response();
        this.movimientoDineroRepository.save(data);
        response.setCode(200);
        response.setMessage("Movimiento registrado exitosamente");
        return response;
    }

}
