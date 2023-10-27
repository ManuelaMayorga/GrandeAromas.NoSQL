package com.uao.GrandeAromas.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.uao.GrandeAromas.Domain.Consulta1DTO;
import com.uao.GrandeAromas.Domain.Consulta2DTO;
import com.uao.GrandeAromas.Domain.ShoppingCartDTO;
import com.uao.GrandeAromas.Model.ShoppingCartModel;    


public interface IShoppingCartService {
    String guardarShoppingCart(ShoppingCartModel shoppingCart);

    List<ShoppingCartModel> obtenerShoppingCarts();

    Optional<ShoppingCartModel> obtenerShoppingCartPorId (int id);

    String actualizarShoppingCartPorId(ShoppingCartModel shoppingCart);

    String eliminarShoppingCartPorId(int id);

    List<Consulta2DTO> obtenerVentas(Date fechaVenta);

    List<ShoppingCartDTO> obtenerVentasdelDia(Date fechaVenta);

    List<Consulta1DTO> obtenerShoppingCartPorUserId (int userId);

    
}
