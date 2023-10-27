package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Model.MembershipModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MembershipRepository extends MongoRepository<MembershipModel, Integer> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}

