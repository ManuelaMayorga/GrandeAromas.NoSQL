package com.uao.GrandeAromas.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.uao.GrandeAromas.Service.IDetailShoppingCartService;
import com.uao.GrandeAromas.Service.IProductsService;
import com.uao.GrandeAromas.Service.IShoppingCartService;
import com.uao.GrandeAromas.Model.DetailShoppingCartModel;




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
        detailShoppingCartService.agregarProducto(detailShoppingCart);
        return new ResponseEntity<String>("Producto agregado al carrito", HttpStatus.OK);
    }    

    @GetMapping("/listarDetailShoppingCarts")
    public ResponseEntity<List<DetailShoppingCartModel>> listarDetailShoppingCarts() {
        return new ResponseEntity<List<DetailShoppingCartModel>>(detailShoppingCartService.obtenerDetailShoppingCarts(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminarDetallePorShoppingCartId/{shoppingCartId}")
    public ResponseEntity<String> eliminarDetallePorShoppingCartId(@PathVariable int shoppingCartId) {
        return new ResponseEntity<String>(detailShoppingCartService.eliminarDetallePorShoppingCartId(shoppingCartId), HttpStatus.OK);
    }

}
