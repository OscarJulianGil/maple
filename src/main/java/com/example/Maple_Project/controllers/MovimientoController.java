package com.example.Maple_Project.controllers;

import com.example.Maple_Project.entities.MovimientoDinero;
import com.example.Maple_Project.services.EmpresaService;
import com.example.Maple_Project.services.Response;
import com.example.Maple_Project.services.MovimientoDineroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.Maple_Project.repository.IEmpresaRepository;

@Controller
@RequestMapping("movimiento")
public class MovimientoController {

    private MovimientoDineroService movimientoDineroService;
    //private EmpresaService empresaService;
    //private IEmpresaRepository empresaRepository;

    public MovimientoController(MovimientoDineroService service) {
        this.movimientoDineroService = service;
    }

    @GetMapping("index")
    public String index(){
        return "movimientos/index";
    }

    @GetMapping("createmovimiento")
    //public String createmovimiento(Model model)
    public String createmovimiento()
    {
        //model.addAttribute("empresa", empresaRepository.findAll());
        return "movimientos/create";
    }

    @PostMapping("createmov")
    public RedirectView create(MovimientoDinero data){
        Response response = this.movimientoDineroService.crearMovimientoDinero(data);
        return new RedirectView("/movimiento/index");

    }

}
