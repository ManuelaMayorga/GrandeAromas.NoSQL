package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Model.UsuariosModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IUsuarioRepository extends MongoRepository<UsuariosModel, Integer> {
    @Query(value = "{'id': ?0}",fields = "{'_id':1,'nameUser':1}")
    UsuariosModel encontrarIdyUsuarioNombre(int userId);
    
}
