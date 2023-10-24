package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.ProductsModel;
import java.util.List;
import java.util.Optional;

public interface IProductsService {
    String agregarProducto(ProductsModel producto);
    
    List<ProductsModel> obtenerProductos();

    Optional<ProductsModel> obtenerProductoById(int productId);

    void actualizarProducto(ProductsModel producto);


}
