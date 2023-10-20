package com.uao.GrandeAromas.Repository;
import com.uao.GrandeAromas.Model.ProductsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductsRepository extends MongoRepository<ProductsModel, Integer> {
    
}
