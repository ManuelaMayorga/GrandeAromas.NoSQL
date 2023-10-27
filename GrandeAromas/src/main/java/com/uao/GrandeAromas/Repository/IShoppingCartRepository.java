package com.uao.GrandeAromas.Repository;

import com.uao.GrandeAromas.Domain.ShoppingCartDTO;
import com.uao.GrandeAromas.Model.ShoppingCartModel;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IShoppingCartRepository extends MongoRepository<ShoppingCartModel, Integer>{

        @Query(value = "{'date':?0 }")
        List<ShoppingCartDTO> obtenerVentasdelDia(Date fechaVenta);

        List<ShoppingCartDTO> findByUserId(int userId);

}
