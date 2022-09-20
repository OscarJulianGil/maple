package com.example.Maple_Project.controllers;


import com.example.Maple_Project.services.UsuarioService;
import com.example.Maple_Project.entities.Usuario;
import com.example.Maple_Project.services.Response;
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

    public LoginController(UsuarioService service){
        this.service = service ;
    }

    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    /*@GetMapping("registro")
    public String registro(Model tiposdocumento){
        //Cargamos los documentos desde la logica de negocio.
        ArrayList<TipoDocumento> tiposDocumentoDB = this.docService.selectAll();
        //Pasamos la infomación al model de thymeleaf
        tiposdocumento.addAttribute("misdocumentos",tiposDocumentoDB);
        tiposdocumento.addAttribute("texto","Bienvenidos");

        return "login/registro";
    }*/

    @PostMapping("postlogin")
    public RedirectView postlogin(Usuario data){
        Response response = this.service.loginUser(data);
        if(response.getCode() == 200){
            return new RedirectView("/movimiento/createmovimiento");
        }
        else{
            return new RedirectView("/login/error");
        }
    }

    /*@PostMapping("postregistro")
    public RedirectView postregisto(registroDTO data){

        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            System.out.println("Contraseña no valida");
            return new RedirectView("/login/error");
        }
        if(!data.getPassword().equals(data.getValidaPassword())){
            System.out.println("Las contraseñas no coinciden.");
            return new RedirectView("/login/error");
        }

        Usuario usuario = new Usuario();

        //Mapping
        usuario.setCorreo(data.getCorreo());
        usuario.setPassword(data.getPassword());
        usuario.setNombre(data.getNombre());

        Response response = this.service.crearUsuario(usuario);
        System.out.println(response.getMessage());
        if(response.getCode() == 200){
            return new RedirectView("/inicio");
        }
        else{
            return new RedirectView("/login/error");
        }
    }

    @GetMapping("error")
    public String error(){
        return "login/error";
    }*/

}
