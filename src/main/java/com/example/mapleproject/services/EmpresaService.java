package com.example.mapleproject.services;

import com.example.mapleproject.entities.Empresa;
import com.example.mapleproject.repository.IEmpresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpresaService {

    private IEmpresaRepository empresaRepository;
    public EmpresaService(IEmpresaRepository repository) {
        this.empresaRepository = repository;
    }

    //El sistema permite consultar todas las empresas
    public ArrayList<Empresa> selectAll() {
        return (ArrayList<Empresa>) empresaRepository.findAll();
    }

    //El sistema permite crear una empresa
    public Response crearEmpresa(Empresa data) {

        //Valida por NIT si el registro ya existe
        ArrayList<Empresa> identificacion = this.empresaRepository.findByNit(data.getNit());

        //Si existe, arroja error
        if (identificacion != null && identificacion.size() > 0) {
            Response response = new Response();
            response.setCode(500);
            response.setMessage("Este NIT ya existe");
            return response;
        }
        //Si no existe, procede con la creación
        Response response = new Response();
        this.empresaRepository.save(data);
        response.setCode(200);
        response.setMessage("Empresa registrada exitosamente");
        return response;
    }

    //El sistema permite consultar una sola empresa
    public Empresa selectById(int Id) {
        Optional<Empresa> validaSiExiste = this.empresaRepository.findById(Id);
        if (validaSiExiste.isPresent()) {
            return validaSiExiste.get();
        } else {
            return null;
        }
    }

    //El sistema permite eliminar una empresa
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

    //El sistema permite editar una empresa
    public Response updateEmpresaById(Empresa data, int Id) {
        Response response = new Response();

        if (Id == 0) {
            response.setCode(500);
            response.setMessage("Error el ID de la empresa no es valido");
            return response;
        }

        //Si la empresa no existe, arroja error
        Empresa exists = selectById(Id);
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error, la empresa no existe en la base de datos");
            return response;
        }

        boolean needUpdate = false;

        //Actualiza la data
        if (StringUtils.hasLength(data.getNombre())) {
            exists.setNombre(data.getNombre());
            needUpdate = true;
        }

        if (StringUtils.hasLength(data.getDireccion())) {
            exists.setDireccion(data.getDireccion());
            needUpdate = true;
        }

        if (data.getTelefono() > 0) {
            exists.setTelefono(data.getTelefono());
            needUpdate = true;
        }

        if (data.getNit() > 0) {
            exists.setNit(data.getNit());
            needUpdate = true;
        }

        if (StringUtils.hasLength(data.getDocumento())) {
            exists.setDocumento(data.getDocumento());
            needUpdate = true;
        }

        //Envia mensaje de actualización exitosa
        if (needUpdate) {
            this.empresaRepository.save(exists);
        }

        response.setCode(200);
        response.setMessage("Empresa modificada exitosamente");
        return response;
    }
}