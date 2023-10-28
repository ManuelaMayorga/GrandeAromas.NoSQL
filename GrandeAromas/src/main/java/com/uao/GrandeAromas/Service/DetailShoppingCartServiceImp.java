package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.DetailShoppingCartModel;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uao.GrandeAromas.Repository.IDetailShoppingCartRepository;

@Service
public class DetailShoppingCartServiceImp implements IDetailShoppingCartService {

    @Autowired
    IDetailShoppingCartRepository detailShoppingCartRepository;

    @Autowired
    IProductsService productsService;

    @Autowired

    IShoppingCartService shoppingCartService;
    @Override
    public String agregarProducto(DetailShoppingCartModel detailShoppingCart) {
        detailShoppingCartRepository.save(detailShoppingCart);
        return "El producto fue agregado exitosamente";
    }

    @Override
    public List<DetailShoppingCartModel> obtenerDetailShoppingCarts() {
        // Implementa la lógica para obtener todos los detalles de carrito de compras.
        // Puedes utilizar detailShoppingCartRepository para realizar la consulta.
        return detailShoppingCartRepository.findAll();
    }

    @Override
    public List<DetailShoppingCartModel> obtenerDetallesPorCarrito(int carritoId) {
        // Utiliza el repositorio para buscar los detalles de compra por el ID del carrito
        List<DetailShoppingCartModel> detalles = detailShoppingCartRepository.findByShoppingCartId(carritoId);
        return detalles;
    }

    @Override
    public String eliminarDetallePorShoppingCartId(int shoppingCartId) {
        // Implementa la lógica para eliminar todos los detalles de carrito de compras
        // por shoppingCartId.
        // Puedes utilizar detailShoppingCartRepository para realizar la consulta.
        detailShoppingCartRepository.deleteByShoppingCartId(shoppingCartId);
        return "El detalle fue eliminado exitosamente";
    }

}

