package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import com.uao.GrandeAromas.Model.ProductsModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uao.GrandeAromas.Repository.IDetailShoppingCartRepository;

@Service
public class DetailShoppingCartServiceImp implements IDetailShoppingCartService {

    @Autowired
    IDetailShoppingCartRepository detailShoppingCartRepository;

    @Autowired
    IProductsService productsService;

    @Override
    public String agregarProducto(DetailShoppingCartModel detailShoppingCart) {
        Optional<ProductsModel> product = productsService.obtenerProductoPorId(detailShoppingCart.getProductId());

        if (product.isEmpty()) {
            return "El producto no existe";
        }

        if (detailShoppingCart.getQuantity() <= 0 || detailShoppingCart.getQuantity() > product.get().getQuantity()) {
            return "La cantidad del producto no es válida o excede la disponibilidad";
        }

        // Agrega la lógica para agregar el producto al carrito aquí.
        // Detalles como guardar en el repositorio detailShoppingCartRepository y otros
        // dependen de tu implementación y flujo de trabajo específico.
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
    public List<DetailShoppingCartModel> obtenerDetallesPorCarrito(int shoppingCartId) {
        // Agrega la lógica para obtener detalles de carrito específicos por ID de carrito aquí.
        // Utiliza detailShoppingCartRepository u otras dependiendo de tu flujo de trabajo.
        return detailShoppingCartRepository.findByShoppingCartId(shoppingCartId);
    }
}

