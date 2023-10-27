package com.uao.GrandeAromas.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.uao.GrandeAromas.Service.IDetailShoppingCartService;
import com.uao.GrandeAromas.Service.IProductsService;
import com.uao.GrandeAromas.Service.IShoppingCartService;
import com.uao.GrandeAromas.Service.IUsuarioService;
import com.uao.GrandeAromas.Domain.ShoppingCartDTO;
import com.uao.GrandeAromas.Enums.OrderStatusEnum;
import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import com.uao.GrandeAromas.Model.ProductsModel;
import com.uao.GrandeAromas.Model.ShoppingCartModel;
import com.uao.GrandeAromas.Model.UsuariosModel;

@RestController
@RequestMapping("/GrandeAromas/shoppingCart")
public class ShoppingCartController {

    @Autowired
    IShoppingCartService shoppingCartService;

    @Autowired 
    IProductsService productsService;

    @Autowired
    IDetailShoppingCartService detailShoppingCartService;

    @Autowired
    IUsuarioService usuarioService;
    @PostMapping("/guardarShoppingCart")
    public ResponseEntity<String> guardarShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartModel shoppingCart = new ShoppingCartModel();
        Date fechaActual = new Date();
        shoppingCart.setId(shoppingCartDTO.getId());
    
        Optional<UsuariosModel> user = this.usuarioService.obtenerUsuariosPorId(shoppingCartDTO.getUserId());
        if (!user.isPresent()) {
            return new ResponseEntity<String>("El usuario con id " + shoppingCartDTO.getUserId() + " no existe.", HttpStatus.BAD_REQUEST);
        }
    
        shoppingCart.setUserId(shoppingCartDTO.getUserId());
        shoppingCart.setAddressInfo(shoppingCartDTO.getAddressInfo());
        shoppingCart.setTotalPrice(shoppingCartDTO.getTotalPrice());
        shoppingCart.setDate(fechaActual);
        shoppingCart.setOrderStatus(OrderStatusEnum.En_Proceso);
    
        List<Map<String, Integer>> detalleVenta = shoppingCartDTO.getDetalleVenta();
        boolean allProductsValid = true; // Variable para rastrear si todos los productos son válidos
    
        for (Map<String, Integer> item : detalleVenta) {
            int productId = item.get("productId");
            int quantity = item.get("quantity");
    
            ProductsModel producto = this.productsService.obtenerProductoPorId(productId).orElse(null);
    
            if (producto == null || quantity < 1 || quantity > producto.getQuantity()) {
                allProductsValid = false; // Marcar como inválido si no cumple con las validaciones
                break; // Salir del bucle si un producto no cumple con las validaciones
            }
        }
    
        if (!allProductsValid) {
            return new ResponseEntity<String>("No se puede crear el carrito porque algunos productos no cumplen con las validaciones.", HttpStatus.BAD_REQUEST);
        }
    
        // Si todos los productos son válidos, ahora realizamos las actualizaciones e inserciones
        for (Map<String, Integer> item : detalleVenta) {
            int productId = item.get("productId");
            int quantity = item.get("quantity");
    
            ProductsModel producto = this.productsService.obtenerProductoPorId(productId).orElse(null);
    
            int currentQuantity = producto.getQuantity();
            double pricePerProduct = producto.getPrice();
    
            producto.setQuantity(currentQuantity - quantity);
            productsService.actualizarProductoPorId(producto);
    
            double totalPriceProducto = pricePerProduct * quantity;
    
            double currentTotalPrice = shoppingCartDTO.getTotalPrice();
            shoppingCartDTO.setTotalPrice(currentTotalPrice + totalPriceProducto);
            shoppingCart.setTotalPrice(shoppingCartDTO.getTotalPrice());
    
            DetailShoppingCartModel detailShoppingCart = new DetailShoppingCartModel();
            detailShoppingCart.setShoppingCartId(shoppingCartDTO.getId());
            detailShoppingCart.setProductId(productId);
            detailShoppingCart.setQuantity(quantity);
    
            detailShoppingCartService.agregarProducto(detailShoppingCart);
        }
    
        return new ResponseEntity<String>(shoppingCartService.guardarShoppingCart(shoppingCart), HttpStatus.OK);
    }

    @GetMapping("/listarShoppingCarts")
    public ResponseEntity<List<ShoppingCartModel>> listarShoppingCarts() {
        return new ResponseEntity<List<ShoppingCartModel>>(shoppingCartService.obtenerShoppingCarts(),HttpStatus.OK);
    }
    

}
