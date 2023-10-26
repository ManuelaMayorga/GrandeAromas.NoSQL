package com.uao.GrandeAromas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.uao.GrandeAromas.Service.IDetailShoppingCartService;
import com.uao.GrandeAromas.Service.IProductsService;
import com.uao.GrandeAromas.Service.IShoppingCartService;

import com.uao.GrandeAromas.Domain.ShoppingCartDTO;
import com.uao.GrandeAromas.Enums.OrderStatusEnum;
import com.uao.GrandeAromas.Exceptions.RecursoNoEncontradoException;
import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import com.uao.GrandeAromas.Model.ProductsModel;
import com.uao.GrandeAromas.Model.ShoppingCartModel;

@RestController
@RequestMapping("/GrandeAromas/shoppingCart")
public class ShoppingCartController {

    @Autowired
    IShoppingCartService shoppingCartService;

    @Autowired 
    IProductsService productsService;

    @Autowired
    IDetailShoppingCartService detailShoppingCartService;

    @PostMapping("/guardarShoppingCart")
    public ResponseEntity<String> guardarShoppingCart( @RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartModel shoppingCart = new ShoppingCartModel();
        Date fechaActual = new Date();
        shoppingCart.setId(shoppingCartDTO.getId());
        shoppingCart.setUserId(shoppingCartDTO.getUserId());
        shoppingCart.setAddressInfo(shoppingCartDTO.getAddressInfo());
        shoppingCart.setTotalPrice(shoppingCartDTO.getTotalPrice());
        shoppingCart.setDate(fechaActual);
        shoppingCart.setOrderStatus(OrderStatusEnum.En_Proceso);
        //Garantizar que existan productos y cantidades del detalle de la venta, si existen
        
        List<Map<String, Integer>> detalleVenta = shoppingCartDTO.getDetalleVenta();
        ProductsModel producto = new ProductsModel();
        int productId = 0;
        int quantity = 0;
        for (int i = 0 ; i < detalleVenta.size() ; i++) {
            productId = detalleVenta.get(i).get("productId");
            producto = this.productsService.obtenerProductoPorId(productId).orElseThrow(()-> new RecursoNoEncontradoException("El producto no existe") );
            quantity = detalleVenta.get(i).get("quantity");

            if (producto != null && quantity <= producto.getQuantity()) {
                
                int currentQuantity = producto.getQuantity();
                double pricePerProduct = producto.getPrice();

                producto.setQuantity(currentQuantity - quantity);
                productsService.actualizarProductoPorId(producto);

                double totalPriceProducto = pricePerProduct * quantity;

                double currentTotalPrice = shoppingCartDTO.getTotalPrice();
                shoppingCartDTO.setTotalPrice(currentTotalPrice + totalPriceProducto);
                shoppingCart.setTotalPrice(shoppingCartDTO.getTotalPrice());
                shoppingCartService.actualizarShoppingCartPorId(shoppingCart);
                
                DetailShoppingCartModel detailShoppingCart = new DetailShoppingCartModel();
                
                detailShoppingCart.setShoppingCartId(shoppingCartDTO.getId());
                detailShoppingCart.setProductId(productId);
                detailShoppingCart.setQuantity(quantity);
                
                detailShoppingCartService.agregarProducto(detailShoppingCart);

            }
        }
        return new ResponseEntity<String>(shoppingCartService.guardarShoppingCart(shoppingCart),HttpStatus.OK);
    }

    @GetMapping("/listarShoppingCarts")
    public ResponseEntity<List<ShoppingCartModel>> listarShoppingCarts() {
        return new ResponseEntity<List<ShoppingCartModel>>(shoppingCartService.obtenerShoppingCarts(),HttpStatus.OK);
    }
    

}
