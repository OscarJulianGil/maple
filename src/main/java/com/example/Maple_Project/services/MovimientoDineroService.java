package com.example.Maple_Project.services;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.entities.MovimientoDinero;
import com.example.Maple_Project.repository.IEmpresaRepository;
import com.example.Maple_Project.repository.IMovimientoDineroRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovimientoDineroService {
    private IMovimientoDineroRepository movimientoDineroRepository;
    private IEmpresaRepository empresaRepository;

    public MovimientoDineroService(IMovimientoDineroRepository repository, IEmpresaRepository erepository) {
        this.movimientoDineroRepository = repository;
        this.empresaRepository = erepository;
    }

    public MovimientoDinero selectById(int Id) {
        Optional<MovimientoDinero> validaSiExisteId = this.movimientoDineroRepository.findById(Id);
        if (validaSiExisteId.isPresent()) {
            return validaSiExisteId.get();
        } else {
            return null;
        }
    }

    public ArrayList<MovimientoDinero> selectAll(int empresaId) {
        return (ArrayList<MovimientoDinero>) movimientoDineroRepository.findByEmpresaId(empresaId);
    }

    public Response crearMovimientoDineroByEmpresa(MovimientoDinero data) {
        Response response = new Response();
        this.movimientoDineroRepository.save(data);
        response.setCode(200);
        response.setMessage("Movimiento registrado exitosamente");
        return response;
    }

    public Response updateMovimientoDineroByEmpresa(MovimientoDinero data, int id) {
        Response response = new Response();

        if (id == 0) {
            response.setCode(500);
            response.setMessage("Error el ID de la empresa no es valido");
            return response;
        }

        //Si la empresa no existe, arroja error
        ArrayList<MovimientoDinero> movimientoEmpresa = selectAll(id);
        if (movimientoEmpresa == null) {
            response.setCode(500);
            response.setMessage("Error, no existen movimientos para la empresa ingresada");
            return response;
        }

        if (data.getId() == 0) {
            response.setCode(500);
            response.setMessage("Error el ID de la empresa no es valido");
            return response;
        }

        MovimientoDinero exists = selectById(data.getId());
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error, no existen movimientos para la empresa ingresada");
            return response;
        }

        boolean needUpdate = false;

        if (data.getMonto() > 0) {
            exists.setMonto(data.getMonto());
            needUpdate = true;
        }

        if (StringUtils.hasLength(data.getConcepto())) {
            exists.setConcepto(data.getConcepto());
            needUpdate = true;

            //Envia mensaje de actualización exitosa

            this.movimientoDineroRepository.save(exists);
            response.setCode(200);
            response.setMessage("Movimientos modificados exitosamente");
            return response;
        }

        public Response deleteMovimientoDinero (Empresa empresa){
            Response response1 = new Response();
            try {
                this.movimientoDineroRepository.deleteMovimientoDineroByEmpresa(empresa);
                response1.setCode(200);
                response1.setMessage("Movimiento de dinero eliminado exitosamente");
                return response1;
            } catch (Exception ex) {
                response1.setCode(500);
                response1.setMessage("Error " + ex.getMessage());
                return response1;
            }
        }
    }
}
