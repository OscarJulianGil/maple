package com.example.mapleproject.controllers;

import com.example.mapleproject.entities.Empresa;
import com.example.mapleproject.services.EmpresaService;
import com.example.mapleproject.services.Response;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class EmpresaController {

    private EmpresaService empresaService;

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

    @PatchMapping("/enterprises/{id}")
    public Response updateEmpresa(@RequestBody Empresa request, @PathVariable("id") int Id){
        return this.empresaService.updateEmpresaById(request, Id);
    }
}