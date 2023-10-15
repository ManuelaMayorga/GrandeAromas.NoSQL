package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Model.UsuariosModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUsuarioRepository extends MongoRepository<UsuariosModel, Integer> {
    
}
