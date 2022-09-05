package com.example.Maple_Project.services;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.repository.IEmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpresaService<id> {

    //propiedad de la interfaz
    private IEmpresaRepository empresaRepository;

    //constructor
    public EmpresaService(IEmpresaRepository repository) {
        this.empresaRepository = repository;
    }

    //Metodo para obtener la lista de empresas de la base de datos
    public ArrayList<Empresa> selectAll() {
        return (ArrayList<Empresa>) empresaRepository.findAll();
    }

    //Metodo para registrar una empresa en la base de datos

    public Response crearEmpresa(Empresa data) {

        //Antes de crearlo debo validar si el nit ya existe
        ArrayList<Empresa> identificacion = this.empresaRepository.findByNit(data.getNit());

        //Si llega con datos es decir que ya existe y me va a responder con el siguiente error
        if (identificacion != null && identificacion.size() > 0) {
            Response response = new Response();
            response.setCode(500);
            response.setMessage("Este NIT ya existe");
            return response;
        }

        Response response = new Response();
        this.empresaRepository.save(data);
        response.setCode(200);
        response.setMessage("Empresa registrada exitosamente");
        return response;
    }

    //Metodo para obtener datos por el id

    public Empresa selectById(int Id) {
        Optional<Empresa> validaSiExiste = this.empresaRepository.findById(Id);
        if (validaSiExiste.isPresent()) {
            return validaSiExiste.get();
        } else {
            return null;
        }
    }

    //Metodo para eliminar un dato a través del id

    public Response deleteEmpresaById(int Id) {
        Response response = new Response();
        try {
            this.empresaRepository.deleteById(Id);
            response.setCode(200);
            response.setMessage("Empresa eliminada exitosamente");
            return response;
        } catch (Exception ex) {
            response.setCode(500);
            response.setMessage("Error " + ex.getMessage());
            return response;
        }
    }

    //Metodo para actualizar un registro de una empresa

    public Response updateEmpresaById(Empresa data, int Id) {
        Response response = new Response();

        if (Id == 0) {
            response.setCode(500);
            response.setMessage("Error el ID de la empresa no es valido");
            return response;
        }

        Empresa exists = selectById(Id);
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error, la empresa no existe en la base de datos");
            return response;
        }

        exists.setNombre(data.getNombre());
        exists.setDocumento(data.getDocumento());
        exists.setDireccion(data.getDireccion());
        exists.setTelefono(data.getTelefono());
        exists.setNit(data.getNit());

        this.empresaRepository.save(exists);
        response.setCode(200);
        response.setMessage("Empresa modificada exitosamente");
        return response;
    }
}

