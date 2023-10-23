package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Model.ShoppingCartModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IShoppingCartRepository extends MongoRepository<ShoppingCartModel, Integer>{
    
}
