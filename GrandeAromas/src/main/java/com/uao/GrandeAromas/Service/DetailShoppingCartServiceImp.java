package com.uao.GrandeAromas.Service;

import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import com.uao.GrandeAromas.Model.ProductsModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uao.GrandeAromas.Repository.IDetailShoppingCartRepository;

@Service
public class DetailShoppingCartServiceImp implements IDetailShoppingCartService{
    
        @Autowired
        IDetailShoppingCartRepository detailShoppingCartRepository;
        
        @Autowired
        IProductsService productsService;
        @Override
        public String agregarProducto(DetailShoppingCartModel detailShoppingCart) {
            
            Optional<ProductsModel> product = productsService.obtenerProductoById(detailShoppingCart.getProductId());

            if(product.isEmpty()){
                return "El producto no existe";
            }

            if (detailShoppingCart.getQuantity() <= 0 || detailShoppingCart.getQuantity() > product.get().getQuantity()) {
                return "La cantidad del producto no es válida o excede la disponibilidad";
            }

            return "El producto fue agregado exitosamente";
        }
    
        @Override
        public List<DetailShoppingCartModel> obtenerDetailShoppingCarts() {
            return detailShoppingCartRepository.findAll();
        }
    

        
}
