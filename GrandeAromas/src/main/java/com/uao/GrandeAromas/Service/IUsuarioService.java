package com.uao.GrandeAromas.Service;

import java.util.List;
import java.util.Optional;

import com.uao.GrandeAromas.Model.UsuariosModel;

public interface IUsuarioService {
    String guardarUsuario(UsuariosModel usuario);

    List<UsuariosModel> obtenerUsuarios();

    String eliminarUsuarioPorId(int userId);

    String actualizarUsuario(UsuariosModel usuario);
    
    UsuariosModel encontrarIdyUsuarioNombre(int userId);

    UsuariosModel encontrarIdyEmail(int userId);

    Optional<UsuariosModel> obtenerUsuariosPorId(int userId);
}
