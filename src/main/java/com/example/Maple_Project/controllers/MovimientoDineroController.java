package com.example.Maple_Project.controllers;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.entities.MovimientoDinero;
import com.example.Maple_Project.entities.Usuario;
import com.example.Maple_Project.services.EmpresaService;
import com.example.Maple_Project.services.MovimientoDineroService;
import com.example.Maple_Project.services.Response;
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
