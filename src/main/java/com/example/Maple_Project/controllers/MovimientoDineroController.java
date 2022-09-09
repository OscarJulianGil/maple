package com.example.Maple_Project.controllers;

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

    public MovimientoDineroController(MovimientoDineroService service){
        this.movimientoDineroService = service;
    }

    @RequestMapping("/enterprises/{id}/movements")
    public ArrayList<MovimientoDinero> getMovimientosByIdEmpresa(@PathVariable Integer id){
        return this.movimientoDineroService.selectAll(id);
    }

    @PostMapping("/enterprises/{id}/movements")
    public Response crearMovimiento(@RequestBody MovimientoDinero data){
        return this.movimientoDineroService.crearMovimientoDinero(data);
    }

    @DeleteMapping("/enterprises/{id}/movements")
    public Response deleteMovimientoDineroByEmpresaById(@PathVariable int empresaId) {
        return this.movimientoDineroService.deleteMovimientoDineroByEmpresaById(empresaId);
    }
}
