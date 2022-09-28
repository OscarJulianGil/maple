package com.example.mapleproject.controllers;

import com.example.mapleproject.entities.Usuario;
import com.example.mapleproject.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    private UsuarioService service;

    //Metodo que me devuelve la informacion del usuario que está autenticado
    protected Usuario seguridad(){
        //En el contexto de seguridad Sprint Security almacena la informacion de autenticacion del usuario, nosotros debemos acceder a ella
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //La autenticación de Sprint Security nos da la llave, en este caso el correo, siempre está en getName
        String currentPrincipalName = authentication.getName();
        //De la logica del negocio, tomo la informacion del usuario
        Usuario usuario = this.service.findByUsername(currentPrincipalName);
        return  usuario;
    }

}