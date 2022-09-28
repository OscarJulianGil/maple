package com.example.mapleproject.controllers;

import com.example.mapleproject.entities.Empresa;
import com.example.mapleproject.entities.MovimientoDinero;
import com.example.mapleproject.services.EmpresaService;
import com.example.mapleproject.services.MovimientoDineroService;
import com.example.mapleproject.services.Response;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
public class MovimientoDineroController {

    private MovimientoDineroService movimientoDineroService;
    private EmpresaService empresaService;

    public MovimientoDineroController(MovimientoDineroService service, EmpresaService eservice) {
        this.movimientoDineroService = service;
        this.empresaService = eservice;
    }

    @RequestMapping("/enterprises/{id}/movements")
    public ArrayList<MovimientoDinero> getMovimientosByIdEmpresa(@PathVariable int empresaid) {
        return this.movimientoDineroService.selectAll(empresaid);
    }

    @RequestMapping("/movements")
    public ArrayList<MovimientoDinero> getUsuarios(){
        return this.movimientoDineroService.selectAll();
    }

    @PostMapping("/enterprises/{id}/movements")
    public Response crearMovimiento(@RequestBody MovimientoDinero data, @PathVariable(name = "id") int id) {
        Empresa empresa = this.empresaService.selectById(id);
        data.setEmpresa(empresa);
        return this.movimientoDineroService.crearMovimientoDineroByEmpresa(data);
    }


    @PostMapping("/movements")
    public Response createMovimientoDinero(@RequestBody MovimientoDinero request){
        return this.movimientoDineroService.crearMovimientoDinero(request);
    }


    @PatchMapping("/enterprises/{id}/movements")
    public Response updateMovimiento(@RequestBody MovimientoDinero data, @PathVariable(name = "id") int id) {
        Empresa empresa = this.empresaService.selectById(id);
        data.setEmpresa(empresa);
        return this.movimientoDineroService.updateMovimientoDineroByEmpresa(data, id);
    }

    @DeleteMapping("/enterprises/{id}/movements")
    public Response deleteMovimientoDinero(@PathVariable("id") int id) {
        Empresa empresa = this.empresaService.selectById(id);
        return this.movimientoDineroService.deleteMovimientoDinero(empresa);
    }
}