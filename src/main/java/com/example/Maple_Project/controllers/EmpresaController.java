package com.example.Maple_Project.controllers;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.services.EmpresaService;
import com.example.Maple_Project.services.Response;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class EmpresaController {

    //propiedad para trabajar con la logica del negocio
    private EmpresaService empresaService;

    //constructor
    public EmpresaController(EmpresaService service){
        this.empresaService = service;
    }

    @RequestMapping("/enterprises")
    public ArrayList<Empresa> getEmpresa(){
        return this.empresaService.selectAll();
    }

    @RequestMapping("/enterprises/{id}")
    public Empresa getIdEmpresa(@PathVariable int id) {
        return this.empresaService.selectById(id);
    }

    @PostMapping("/enterprises")
    public Response createEmpresa(@RequestBody Empresa request){
        return this.empresaService.crearEmpresa(request);
    }

    @DeleteMapping("/enterprises/{id}")
    public Response deleteEmpresa(@PathVariable int id) {
        return this.empresaService.deleteEmpresaById(id);
    }

    @PutMapping("/enterprises/{id}")
    public Response updateEmpresa(@RequestBody Empresa request, @PathVariable("id") int Id){
        return this.empresaService.updateEmpresaById(request, Id);
    }



    /*@GetMapping("/empresa/{nit}")
    public ArrayList<Empresa> getEmpresaByNit(@PathVariable int nit){
        try{
            return this.empresaService.obtenerPorNit(nit);
        }
        catch(Exception e){

        }

        return null;
    }*/

}