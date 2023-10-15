package com.uao.GrandeAromas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uao.GrandeAromas.Model.UsuariosModel;
import com.uao.GrandeAromas.Repository.IUsuarioRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public String guardarUsuario(UsuariosModel usuario) {
        usuarioRepository.save(usuario);
        return "Usuario guardado";
    }

    @Override
    public List<UsuariosModel> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
}