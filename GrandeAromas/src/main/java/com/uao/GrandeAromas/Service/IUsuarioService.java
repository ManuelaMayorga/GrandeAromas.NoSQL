package com.uao.GrandeAromas.Service;

import java.util.List;

import com.uao.GrandeAromas.Model.UsuariosModel;

public interface IUsuarioService {
    String guardarUsuario(UsuariosModel usuario);

    List<UsuariosModel> obtenerUsuarios();
}
