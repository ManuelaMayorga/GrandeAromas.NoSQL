package com.uao.GrandeAromas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uao.GrandeAromas.Domain.Consulta3DTO;
import com.uao.GrandeAromas.Enums.CategoryEnum;
import com.uao.GrandeAromas.Enums.UnitEnum;
import com.uao.GrandeAromas.Exceptions.RecursoNoEncontradoException;
import com.uao.GrandeAromas.Model.ProductsModel;
import com.uao.GrandeAromas.Service.IProductsService;
import  org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


    @DeleteMapping("/eliminarProducto/{productId}")
    public ResponseEntity<String> eliminarProductoPorId(@PathVariable int productId) {
        Optional<ProductsModel> optionalProduct = productsService.obtenerProductoPorId(productId);

        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<String>("Producto con el id: " + productId + " no Encontrado.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(productsService.eliminarProductoPorId(productId), HttpStatus.OK);
    }

    @PutMapping("/actualizarProducto/{productId}")
    public ResponseEntity<String> actualizarProductoPorId(@PathVariable int productId, @RequestBody ProductsModel productDetail) {
    ProductsModel product = productsService.obtenerProductoPorId(productId).orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con el id: " + productId));
    
    product.setCategory(productDetail.getCategory());
    product.setDescription(productDetail.getDescription());
    product.setQuantity(productDetail.getQuantity());
    product.setUnit(productDetail.getUnit());
    product.setPrice(productDetail.getPrice());
    
    
    
    if (productDetail.getCategory() != null && 
    (productDetail.getCategory() == CategoryEnum.al_detal || 
     productDetail.getCategory() == CategoryEnum.al_por_mayor || 
     productDetail.getCategory() == CategoryEnum.maquinas) &&
    !productDetail.getDescription().isEmpty() && 
    productDetail.getQuantity() > 0 && 
    productDetail.getPrice() != 0.0 && 
    productDetail.getUnit() != null && 
    (productDetail.getUnit() == UnitEnum.Gr || 
     productDetail.getUnit() == UnitEnum.Kg || 
     productDetail.getUnit() == UnitEnum.Unds)) {
    productsService.actualizarProductoPorId(product);
    return new ResponseEntity<String>(productsService.actualizarProductoPorId(product), HttpStatus.OK);
    } 
    else {
    return new ResponseEntity<String>("No se puede actualizar el producto con el id: " + productId + " porque no se enviaron todos los datos o los datos son inválidos", HttpStatus.BAD_REQUEST);
    }
    }


    @GetMapping("/buscarProducto/{productId}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable int productId) {
        Optional<ProductsModel> product = productsService.obtenerProductoPorId(productId);
        if (!product.isPresent()) {
            return new ResponseEntity<String>("Producto no encontrado para el ID: " + productId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @GetMapping("/stock")
    public ResponseEntity<List<Consulta3DTO>> getProductStock() {
        List<Consulta3DTO> productStockList = productsService.getProductsStock();

        if (!productStockList.isEmpty()) {
            return new ResponseEntity<>(productStockList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    
}
