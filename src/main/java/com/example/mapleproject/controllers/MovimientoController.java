package com.example.mapleproject.controllers;

import com.example.mapleproject.entities.MovimientoDinero;
import com.example.mapleproject.services.EmpresaService;
import com.example.mapleproject.services.MovimientoDineroService;
import com.example.mapleproject.services.Response;
import com.example.mapleproject.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("movimiento")
public class MovimientoController {

    private MovimientoDineroService movimientoDineroService;

    private UsuarioService service;
    private EmpresaService empService;

    public MovimientoController(MovimientoDineroService ser) {
        this.movimientoDineroService = ser;
    }

    //Controlador que me está retornando la vista en HTML, le va pasar toda la lista de movimientos en base de datos
    @GetMapping("index")
    public String index(Model model){//Pasar modelo de datos a traves del atributo Model, el modelo se llama movimientos
        //Consulto los movimientos a la base de datos por medio de la lógica de negocio
        ArrayList<MovimientoDinero> movimientosDB = this.movimientoDineroService.selectAll();//Lleno el modelo movimientos
        //Armo el modelo que se le va a pasar a Thymeleaf
        model.addAttribute("mismovimientos", movimientosDB);//Llave que voy a llenar de los movimientos
        Long sumaMonto = movimientoDineroService.obtenerSumaMontos();
        model.addAttribute("SumaMontos",sumaMonto);//Mandamos la suma de todos los montos a la plantilla
        return "movimientos/tables";
    }

    @GetMapping("createmovimiento")
    public String create(){
        return "movimientos/create";
    }


    //Este controlador recibirá un POST que se llama createmov
    @PostMapping("createmov")
    public RedirectView create(MovimientoDinero data){
        Response response = this.movimientoDineroService.crearMovimientoDinero(data);
        return new RedirectView("/movimiento/index");

    }
}