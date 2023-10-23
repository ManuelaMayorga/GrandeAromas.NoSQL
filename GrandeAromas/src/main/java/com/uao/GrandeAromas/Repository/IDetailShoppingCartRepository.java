package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDetailShoppingCartRepository extends MongoRepository<DetailShoppingCartModel, Integer> {
    
}
