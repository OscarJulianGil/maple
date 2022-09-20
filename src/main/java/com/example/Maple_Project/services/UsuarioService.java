package com.example.Maple_Project.services;

import com.example.Maple_Project.entities.Usuario;
import com.example.Maple_Project.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {
    private IUsuarioRepository usuarioRepository;
    public UsuarioService(IUsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    //El sistema permite consultar todos los usuarios
    public ArrayList<Usuario> selectAll() {
        return (ArrayList<Usuario>) this.usuarioRepository.findAll();
    }

    //El sistema permite crear un usuario
    public Response crearUsuario(Usuario data) {
        Response response = new Response();
        this.usuarioRepository.save(data);
        response.setCode(200);
        response.setMessage("Usuario registrado exitosamente");
        return response;
    }

    //El sistema permite consultar un solo usuario
    public Usuario selectById(int Id) {
        Optional<Usuario> revisarSiExiste = this.usuarioRepository.findById(Id);
        if (revisarSiExiste.isPresent()) {
            return revisarSiExiste.get();

        } else {
            return null;
        }
    }

    //El sistema permite eliminar un usuario
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
    //El sistema permite editar un usuario
    public Response updateUsuarioById(Usuario data, int Id) {
        Response response = new Response();

        if (Id == 0) {
            response.setCode(500);
            response.setMessage("Error el ID del usuario no es valido");
            return response;
        }

        //Si el usuario no existe, arroja error
        Usuario exists = selectById(Id);
        if (exists == null) {
            response.setCode(500);
            response.setMessage("Error, el usuario no existe en la base de datos");
            return response;
        }

        //Si ingresa un dato null o vacio, arroja error
        if (data.getCorreo().equals(null) || data.getCorreo().equals("")) {
            response.setCode(500);
            response.setMessage("Error, el correo electronico no es valido");
            return response;
        }

        //Si ingresa un dato null o vacio, arroja error
        if (data.getNombre().equals(null) || data.getNombre().equals("")) {
            response.setCode(500);
            response.setMessage("Error, el Nombre no es valido");
            return response;
        }

        //Actualiza la data

        boolean needUpdate = false;

        if (StringUtils.hasLength(data.getNombre())) {
            exists.setNombre(data.getNombre());
            needUpdate = true;
        }

        if (StringUtils.hasLength(data.getCorreo())) {
            exists.setCorreo(data.getCorreo());
            needUpdate = true;
        }

        //Envia mensaje de actualización exitosa
        if (needUpdate){
            this.usuarioRepository.save(exists);
        }
        response.setCode(200);
        response.setMessage("Usuario modificado exitosamente");
        return response;
    }

    //Logeo

    public Response loginUser(Usuario data){
        Response response = new Response();

        //Logica de negocio
        //Validamos datos


        //Validamos password
        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            response.setCode(500);
            response.setMessage("Error, su contraseña no es válida.");
            return  response;
        }

        ArrayList<Usuario> existe = this.usuarioRepository.validaCredenciales(data.getCorreo(),data.getPassword());
        if(existe != null && existe.size() > 0){
            response.setCode(200);
            response.setMessage("Usuario autenticado exitosamente.");
            return  response;
        }

        response.setCode(500);
        response.setMessage("Error, sus datos de acceso no son válidos");
        return  response;
    }

}