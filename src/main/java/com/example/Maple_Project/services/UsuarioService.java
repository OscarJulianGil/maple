package com.example.Maple_Project.services;

import com.example.Maple_Project.entities.Empresa;
import com.example.Maple_Project.entities.Usuario;
import com.example.Maple_Project.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {

    //propiedad de la interfaz
    private IUsuarioRepository usuarioRepository;

    //constructor
    public UsuarioService(IUsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    //Metodo para obtener la lista de usuarios de la base de datos
    public ArrayList<Usuario> selectAll() {
        return (ArrayList<Usuario>) this.usuarioRepository.findAll();
    }

    ///Metodo para crear la lista de usuarios para la base de datos
    public Response crearUsuario(Usuario data) {
        Response response = new Response();
        this.usuarioRepository.save(data);
        response.setCode(200);
        response.setMessage("Usuario registrado exitosamente");
        return response;
    }

    ///Metodo para obtener datos por el Id
    public Usuario selectById(int Id) {
        Optional<Usuario> revisarSiExiste = this.usuarioRepository.findById(Id);
        if (revisarSiExiste.isPresent()) {
            return revisarSiExiste.get();

        } else {
            return null;
        }
    }

    ///Metodo que nos va permitir eliminiar un usuario

    public Response deleteUsuarioById(int Id) {
        Response response = new Response();
        try {
            this.usuarioRepository.deleteById(Id);
            response.setCode(200);
            response.setMessage("El usuario ha sido eliminado exitosamente");
            return response;
        } catch (Exception ex) {
            response.setCode(500);
            response.setMessage("Error " + ex.getMessage());
            return response;
        }

    }

    public Response updateUsuarioById(Usuario data, int Id) {
        Response response = new Response();


        if (Id == 0) {
            response.setCode(500);
            response.setMessage("Error el ID del usuario no es valido");
            return response;
        }

        Usuario exists = selectById(Id);
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error, el usuario no existe en la base de datos");
            return response;
        }

        if (data.getCorreo().equals(null) || data.getCorreo().equals("")) {
            response.setCode(500);
            response.setMessage("Error, el correo electronico no es valido");
            return response;
        }

        if (data.getNombre().equals(null) || data.getNombre().equals("")) {
            response.setCode(500);
            response.setMessage("Error, el Nombre no es valido");
            return response;
        }

        exists.setNombre(data.getNombre());
        exists.setCorreo(data.getCorreo());


        this.usuarioRepository.save(exists);
        response.setCode(200);
        response.setMessage("Usuario modificado exitosamente");
        return response;
    }
}