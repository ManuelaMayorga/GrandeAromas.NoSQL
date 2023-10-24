package com.uao.GrandeAromas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import com.uao.GrandeAromas.Repository.IShoppingCartRepository;
import com.uao.GrandeAromas.Model.ShoppingCartModel;

@Service
public class ShoppingCartServiceImp implements IShoppingCartService{

    @Autowired
    IShoppingCartRepository shoppingCartRepository;

    @Override
    public String guardarShoppingCart(ShoppingCartModel shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
        return "El carrito de compras fue creado exitosamente";
    }

    @Override
    public List<ShoppingCartModel> obtenerShoppingCarts() {
        return shoppingCartRepository.findAll();
    }


    

    
}
