package com.uao.GrandeAromas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.uao.GrandeAromas.Service.IShoppingCartService;
import com.uao.GrandeAromas.Enums.OrderStatusEnum;
import com.uao.GrandeAromas.Model.ShoppingCartModel;

@RestController
@RequestMapping("/GrandeAromas/shoppingCart")
public class ShoppingCartController {

    @Autowired
    IShoppingCartService shoppingCartService;

    @PostMapping("/guardarShoppingCart")
    public ResponseEntity<String> guardarShoppingCart(@RequestBody ShoppingCartModel shoppingCart) {;
        //Agregar la fecha del sistema
        Date fechaActual = new Date();
        shoppingCart.setDate(fechaActual);
        shoppingCart.setOrderStatus(OrderStatusEnum.En_Proceso);
        //Garantizar que existan productos y cantidades del detalle de la venta, si existen 
        shoppingCartService.guardarShoppingCart(shoppingCart);
        // De shoppingCart recuperar cada detalle de compra 
        // Si no existen productos o cantidades del detalle de la venta, retornar un error
        return new ResponseEntity<String>(shoppingCartService.guardarShoppingCart(shoppingCart),HttpStatus.OK);
    }

    @GetMapping("/listarShoppingCarts")
    public ResponseEntity<List<ShoppingCartModel>> listarShoppingCarts() {
        return new ResponseEntity<List<ShoppingCartModel>>(shoppingCartService.obtenerShoppingCarts(),HttpStatus.OK);
    }
    

}
