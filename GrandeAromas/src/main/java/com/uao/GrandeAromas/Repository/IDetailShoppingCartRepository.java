package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface IDetailShoppingCartRepository extends MongoRepository<DetailShoppingCartModel, Integer> {
    List<DetailShoppingCartModel> findByShoppingCartId(int shoppingCartId);
}

