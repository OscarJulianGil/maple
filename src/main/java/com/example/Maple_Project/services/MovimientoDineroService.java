package com.example.Maple_Project.services;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.entities.MovimientoDinero;
import com.example.Maple_Project.repository.IMovimientoDineroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class MovimientoDineroService {
    private IMovimientoDineroRepository movimientoDineroRepository;

    public MovimientoDineroService (IMovimientoDineroRepository repository) {
        this.movimientoDineroRepository = repository;
    }

    public ArrayList<MovimientoDinero> selectAll(int empresaId){
        return (ArrayList<MovimientoDinero>) movimientoDineroRepository.findByEmpresaId(empresaId);
    }

    public Response crearMovimientoDinero (MovimientoDinero data){
        Response response = new Response();
        this.movimientoDineroRepository.save(data);
        response.setCode(200);
        response.setMessage("Movimiento registrado exitosamente");
        return response;
    }


    //Esto es nuevo 20220908
    public Response deleteMovimientoDineroByEmpresaById(int empresaId) {
        Response response = new Response();
        try {
            this.movimientoDineroRepository.deleteById(empresaId);
            response.setCode(200);
            response.setMessage("Movimiento de dinero eliminado exitosamente");
            return response;
        } catch (Exception ex) {
            response.setCode(500);
            response.setMessage("Error " + ex.getMessage());
            return response;
        }
    }



}
