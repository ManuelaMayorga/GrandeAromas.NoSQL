package com.uao.GrandeAromas.Service;

import org.springframework.stereotype.Service;
import com.uao.GrandeAromas.Model.ProductsModel;
import com.uao.GrandeAromas.Repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImp implements IProductsService {
    
    @Autowired
    IProductsRepository productsRepository;

    @Override
    public String agregarProducto(ProductsModel producto) {
        productsRepository.save(producto);
        return "El producto" + producto.getProductName() + "fue creado exitosamente";
    }

    @Override
    public List<ProductsModel> obtenerProductos() {
        return productsRepository.findAll();
    }

    @Override
    public Optional<ProductsModel> obtenerProductoPorId(int productId) {
        return productsRepository.findById(productId);
    }

    @Override
    public String actualizarProductoPorId(ProductsModel product) {
        productsRepository.save(product);
        return "El producto " + product.getProductName() + " fue actualizado exitosamente";
    }

    @Override
    public String eliminarProductoPorId(int productId) {
        productsRepository.deleteById(productId);
        return "El producto con el id: " + productId + " fue eliminado exitosamente";
    }
}
