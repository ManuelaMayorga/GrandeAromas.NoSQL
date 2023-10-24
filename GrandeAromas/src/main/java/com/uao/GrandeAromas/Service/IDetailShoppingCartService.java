package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.DetailShoppingCartModel;

import java.util.List;


public interface IDetailShoppingCartService {
    
    String agregarProducto(DetailShoppingCartModel detailShoppingCart);

    List<DetailShoppingCartModel> obtenerDetailShoppingCarts();

    List<DetailShoppingCartModel> obtenerDetallesPorCarrito(int shoppingCartId);

    

}
