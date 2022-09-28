package com.example.mapleproject.controllers;


import com.example.mapleproject.dto.registroDTO;
import com.example.mapleproject.entities.Empresa;
import com.example.mapleproject.entities.Usuario;
import com.example.mapleproject.services.EmpresaService;
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
@RequestMapping("login")
public class LoginController {

    private UsuarioService service;
    private EmpresaService empService;

    public LoginController(UsuarioService service, EmpresaService empService) {
        this.service = service;
        this.empService = empService;
    }

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("inicio")
    public String inicio() {
        return "inicio/inicio2";
    }

    //Recibe el login y hace el redireccionamiento
    @PostMapping("postlogin")
    public RedirectView postlogin(Usuario data) {
        Response response = this.service.loginUser(data);
        if (response.getCode() == 200) {
            return new RedirectView("/login/inicio");
        } else {
            return new RedirectView("/login/error");
        }
    }

    /*@GetMapping("errorindex")
    public String errorindex() {
        return "inicio/404";
    }*/

    //Para pasar datos del controlador a la vista, necesitamos declarar un Modelo, en este caso el parametro empresas
    @GetMapping("registro")
    public String registro(Model empresas) {
        //Cargamos las empresas desde la logica de negocio.
        ArrayList<Empresa> empresasDB = this.empService.selectAll();
        //Pasamos la infomación al model de thymeleaf
        empresas.addAttribute("misempresas", empresasDB);//Le estoy pasando info del controlador a la vista
        return "login/registro";
    }

    //Controlador POST que va a recibir un formulario con datos
    @PostMapping("postregistro")
    public RedirectView postregistro(registroDTO data) {
        if (data.getPassword().equals(null) || data.getPassword().equals("")) {
            System.out.println("Contraseña no valida");
            return new RedirectView("/login/error");
        }
        if (!data.getPassword().equals(data.getValidaPassword())) {
            System.out.println("Las contraseñas no coinciden.");
            return new RedirectView("/login/error");
        }

        Usuario user = new Usuario();

        //Estoy trayendo la informacion del objeto registroDTO data, al objeto que me pide mi logica de negocio
        user.setNombre(data.getNombre());
        user.setCorreo(data.getCorreo());
        user.setPassword(data.getPassword());
        user.setEmpresa(data.getEmpresa());

        Response response = this.service.crearUsuario(user);
        System.out.println(response.getMessage());
        if(response.getCode() == 200){
            return new RedirectView("/login/login");
        }
        else{
            return new RedirectView("/login/error");
        }
    }

    @GetMapping("error")
    public String error(){
        return "/login/error";}

}