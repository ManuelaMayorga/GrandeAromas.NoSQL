package com.uao.GrandeAromas.Controller;

import com.uao.GrandeAromas.Exceptions.RecursoNoEncontradoException;
import com.uao.GrandeAromas.Model.UsuariosModel;
import com.uao.GrandeAromas.Service.IUsuarioService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping("/GrandeAromas/usuarios")
public class UsuariosController {
    @Autowired
    IUsuarioService usuarioService;
    @PostMapping("/guardarUsuario")
    public ResponseEntity<String> guardarUsuario(@RequestBody UsuariosModel usuario) {
        usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<String>(usuarioService.guardarUsuario(usuario),HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{userId}")
    public ResponseEntity<String> eliminarUsuarioPorId(@PathVariable int userId) {
    Optional<UsuariosModel> optionalUsuario = usuarioService.obtenerUsuariosPorId(userId);
    
    if (!optionalUsuario.isPresent()) {
        return new ResponseEntity<String>("Usuario no Encontrado", HttpStatus.NOT_FOUND);
    }
    
    return new ResponseEntity<String>(usuarioService.eliminarUsuarioPorId(userId), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{userId}")
    public ResponseEntity<String> actualizarUsuarioPorId(@PathVariable int userId, @RequestBody UsuariosModel userDetail) {
    UsuariosModel usuario = usuarioService.obtenerUsuariosPorId(userId).orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el id: " + userId));

    usuario.setNameUser(userDetail.getNameUser());
    usuario.setEmail(userDetail.getEmail());
    usuario.setPassword(userDetail.getPassword());

    if (!userDetail.getNameUser().isEmpty() && !userDetail.getEmail().isEmpty() && !userDetail.getPassword().isEmpty()) {
        usuarioService.actualizarUsuario(usuario);
        return new ResponseEntity<String>(usuarioService.actualizarUsuario(usuario),HttpStatus.OK);
    } 
    else {
        throw new RecursoNoEncontradoException("No se puede actualizar el usuario con el id: " + userId + " porque no se enviaron todos los datos");
    }
    }

    @GetMapping("/obtenerUsuario/{userId}")
public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int userId) {
    Optional<UsuariosModel> usuario = usuarioService.obtenerUsuariosPorId(userId);
    if (usuario.isPresent()) {
        return new ResponseEntity<Optional<UsuariosModel>>(usuario, HttpStatus.OK);
    } else {
        return new ResponseEntity<String>("El usuario con el ID " + userId + " no existe.", HttpStatus.NOT_FOUND);
    }
    }
    
    @GetMapping("/obtenerUsuarios")
    public ResponseEntity<List<UsuariosModel>>obtenerUsuarios() {
        return new ResponseEntity<List<UsuariosModel>>(usuarioService.obtenerUsuarios(),HttpStatus.OK);
    }
}
