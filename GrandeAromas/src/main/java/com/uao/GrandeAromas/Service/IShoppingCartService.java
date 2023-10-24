package com.uao.GrandeAromas.Service;

import java.util.List;

import com.uao.GrandeAromas.Model.ShoppingCartModel;    


public interface IShoppingCartService {
    String guardarShoppingCart(ShoppingCartModel shoppingCart);

    List<ShoppingCartModel> obtenerShoppingCarts();


    
}
