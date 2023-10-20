package com.uao.GrandeAromas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uao.GrandeAromas.Model.ProductsModel;
import com.uao.GrandeAromas.Service.IProductsService;
import  org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/GrandeAromas/products")
public class ProductsController {
    @Autowired
    IProductsService productsService;
    @PostMapping("/agregarProducto")
    public ResponseEntity<String> agregarProducto(@RequestBody ProductsModel producto) {
        productsService.agregarProducto(producto);
        return new ResponseEntity<String>(productsService.agregarProducto(producto),HttpStatus.OK);
    }

    @GetMapping("/obtenerProductos")
    public ResponseEntity<List<ProductsModel>>obtenerProductos() {
        return new ResponseEntity<List<ProductsModel>>(productsService.obtenerProductos(),HttpStatus.OK);
    }

}
