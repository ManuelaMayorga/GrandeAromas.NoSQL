package com.uao.GrandeAromas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uao.GrandeAromas.Model.UsuariosModel;
import com.uao.GrandeAromas.Repository.IUsuarioRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public UsuariosModel encontrarIdyEmail(int userId) {
        UsuariosModel emailEncontrado = new UsuariosModel();
        UsuariosModel emailBD = usuarioRepository.encontrarIdyEmail(userId);
        emailEncontrado.setId(emailBD.getId());
        emailEncontrado.setEmail(emailBD.getEmail());
        return emailEncontrado;
    }
    
    @Override
    public String guardarUsuario(UsuariosModel usuario) {
        usuarioRepository.save(usuario);
        return "Usuario guardado";
    }

    @Override
    public String eliminarUsuarioPorId(int userId) {
        if (usuarioRepository.existsById(userId)) {
            usuarioRepository.deleteById(userId);
        }
        return "Usuario con id: " + userId + " eliminado";
    }

    @Override
    public List<UsuariosModel> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
    @Override
    public UsuariosModel encontrarIdyUsuarioNombre(int userId) {
        UsuariosModel usuarioEncontrado= new UsuariosModel();
        UsuariosModel usuarioBD=usuarioRepository.encontrarIdyUsuarioNombre(userId);
        usuarioEncontrado.setId(usuarioBD.getId());
        usuarioEncontrado.setNameUser(usuarioBD.getNameUser());
        return usuarioEncontrado;
        }

    @Override
    public Optional<UsuariosModel> obtenerUsuariosPorId(int userId) {
        return usuarioRepository.findById(userId);
    }

    @Override
    public String actualizarUsuario(UsuariosModel usuario) {
        usuarioRepository.save(usuario);
        return "El usuario con id: " + usuario.getId() + " ha sido actualizado";
    }
}
