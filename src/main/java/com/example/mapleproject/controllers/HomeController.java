package com.example.mapleproject.controllers;

import com.example.mapleproject.entities.Usuario;
import com.example.mapleproject.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

//Va a ser el controlador inicial que recibe todas las peticiones despues que un usuario haga login
@Controller
public class HomeController extends BaseController {

    @Autowired
    private UsuarioService service;

    @GetMapping("inicio")
    public String inicio(Model data, @AuthenticationPrincipal OidcUser principal){
        if(principal != null) {
            Map<String, Object> mapa = principal.getClaims();
            Usuario user = this.service.findByUsername((String)mapa.get("email"));
            data.addAttribute("autenticado", user);
            System.out.println(mapa);
        }
        else {
            data.addAttribute("autenticado", seguridad());
        }
        return "login/inicio";
    }

}