package com.uao.GrandeAromas.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import com.uao.GrandeAromas.Service.IDetailShoppingCartService;
import com.uao.GrandeAromas.Service.IProductsService;
import com.uao.GrandeAromas.Service.IShoppingCartService;
import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import com.uao.GrandeAromas.Model.ProductsModel;
import com.uao.GrandeAromas.Model.ShoppingCartModel;


@RestController
@RequestMapping("/GrandeAromas/detailShoppingCart")
public class DetailShoppingCartController {

    @Autowired
    IDetailShoppingCartService detailShoppingCartService;
    @Autowired
    IProductsService productService; 
    @Autowired
    IShoppingCartService shoppingCartService;

    @PostMapping("/agregarProducto")
    public ResponseEntity<String> agregarProducto(@RequestBody DetailShoppingCartModel detailShoppingCart) {
        

        ProductsModel product = productService.obtenerProductoPorId(detailShoppingCart.getProductId()).orElse(null);
        if (product != null) {
            int quantityInCart = detailShoppingCart.getQuantity(); 
            int currentQuantity = product.getQuantity();
            double pricePerProduct = product.getPrice();
            if (currentQuantity >= quantityInCart) {
               
                product.setQuantity(currentQuantity - quantityInCart);

                productService.actualizarProductoPorId(product); 

                productService.actualizarProductoPorId(product); 

                double totalPriceProducto = pricePerProduct * quantityInCart;

                ShoppingCartModel shoppingCart = shoppingCartService.obtenerShoppingCartPorId(detailShoppingCart.getShoppingCartId()).orElse(null);

                if (shoppingCart != null) {
                    double currentTotalPrice = shoppingCart.getTotalPrice();
                    shoppingCart.setTotalPrice(currentTotalPrice + totalPriceProducto);
                    shoppingCartService.actualizarShoppingCart(shoppingCart);
                } else {
                    return new ResponseEntity<String>("Carrito de compras no encontrado", HttpStatus.NOT_FOUND);
                }

                detailShoppingCartService.agregarProducto(detailShoppingCart);
                
            } else {
                return new ResponseEntity<String>("No hay suficiente cantidad disponible", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
        

        detailShoppingCartService.agregarProducto(detailShoppingCart);
        return new ResponseEntity<String>("Producto agregado al carrito", HttpStatus.OK);
    }


    @GetMapping("/listarDetailShoppingCarts")
    public ResponseEntity<List<DetailShoppingCartModel>> listarDetailShoppingCarts() {
        return new ResponseEntity<List<DetailShoppingCartModel>>(detailShoppingCartService.obtenerDetailShoppingCarts(), HttpStatus.OK);
    }

}
