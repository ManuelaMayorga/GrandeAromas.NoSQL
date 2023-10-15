package com.uao.GrandeAromas.Controller;

import com.uao.GrandeAromas.Model.UsuariosModel;
import com.uao.GrandeAromas.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController 
@RequestMapping("/GrandeAromas/usuarios")
public class UsuariosController {
    @Autowired
    IUsuarioService usuarioService;
    @PostMapping("/guardarUsuario")
    public ResponseEntity<String> guardarUsuario(@RequestBody UsuariosModel usuario) {
        if (usuario.getAffiliation_id() == -1) {
            usuario.setAffiliation_id(-1);
        }
        usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<String>(usuarioService.guardarUsuario(usuario),HttpStatus.OK);
    }

    @GetMapping("/obtenerUsuarios")
    public ResponseEntity<List<UsuariosModel>>obtenerUsuarios() {
        return new ResponseEntity<List<UsuariosModel>>(usuarioService.obtenerUsuarios(),HttpStatus.OK);
    }
}
