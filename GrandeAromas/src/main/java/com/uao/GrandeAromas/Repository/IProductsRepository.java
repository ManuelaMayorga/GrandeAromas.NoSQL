package com.uao.GrandeAromas.Repository;
import com.uao.GrandeAromas.Domain.Consulta3DTO;
import com.uao.GrandeAromas.Model.ProductsModel;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IProductsRepository extends MongoRepository<ProductsModel, Integer> {

    @Query("{'category': { $ne: null }}")
    List<Consulta3DTO> getProductsStock();    
}
