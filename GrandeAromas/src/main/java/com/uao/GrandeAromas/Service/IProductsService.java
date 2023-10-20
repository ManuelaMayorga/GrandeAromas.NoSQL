package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.ProductsModel;
import java.util.List;

public interface IProductsService {
    String agregarProducto(ProductsModel producto);
    
    List<ProductsModel> obtenerProductos();
}
