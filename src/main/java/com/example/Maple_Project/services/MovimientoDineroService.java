package com.example.Maple_Project.services;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.entities.MovimientoDinero;
import com.example.Maple_Project.repository.IMovimientoDineroRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

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

    public Response updateMovimientoDineroByEmpresaById(MovimientoDinero data, int empresaId) {
        Response response = new Response();

        if (empresaId == 0) {
            response.setCode(500);
            response.setMessage("Error el ID de la empresa no es valido");
            return response;
        }

        //Si la empresa no existe, arroja error
        ArrayList<MovimientoDinero> exists = selectAll(empresaId);
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error, no existen movimientos para la empresa ingresada");
            return response;
        }


        //Envia mensaje de actualización exitosa
        //if (needUpdate) {
        //    this.movimientoDineroRepository.save(exists);
        //}

        response.setCode(200);
        response.setMessage("Movimientos modificados exitosamente");
        return response;
    }
}
