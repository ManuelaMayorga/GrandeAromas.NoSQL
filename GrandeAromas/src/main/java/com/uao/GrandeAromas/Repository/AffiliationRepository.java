package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Model.AffiliationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AffiliationRepository extends MongoRepository<AffiliationModel, Integer> {
    // Puedes agregar consultas personalizadas si es necesario
}