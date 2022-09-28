package com.example.mapleproject.controllers;

import com.example.mapleproject.entities.Usuario;
import com.example.mapleproject.services.Response;
import com.example.mapleproject.services.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService service){
        this.usuarioService =  service;
    }

    @RequestMapping("/users")
    public ArrayList<Usuario> getUsuarios(){
        return this.usuarioService.selectAll();
    }

    @PostMapping("/users")
    public Response createUsuario(@RequestBody Usuario request){
        return this.usuarioService.crearUsuario(request);
    }

    @RequestMapping("/users/{id}")
    public Usuario getIdUsuario(@PathVariable int id){
        return this.usuarioService.selectById(id);
    }

    @DeleteMapping("/users/{id}")
    public Response deleteUsuario(@PathVariable int id){
        return this.usuarioService.deleteUsuarioById(id);
    }

    @PatchMapping("/users/{id}")
    public Response updateUsuario(@RequestBody Usuario request,@PathVariable("id")int id){
        return this.usuarioService.updateUsuarioById(request, id);
    }
}