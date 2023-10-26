package com.uao.GrandeAromas.Service;

import java.util.List;
import java.util.Optional;

import com.uao.GrandeAromas.Model.ShoppingCartModel;    


public interface IShoppingCartService {
    String guardarShoppingCart(ShoppingCartModel shoppingCart);

    List<ShoppingCartModel> obtenerShoppingCarts();

    Optional<ShoppingCartModel> obtenerShoppingCartPorId (int id);

    String actualizarShoppingCartPorId(ShoppingCartModel shoppingCart);
    
}
