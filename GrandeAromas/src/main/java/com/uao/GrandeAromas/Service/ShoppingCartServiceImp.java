package com.uao.GrandeAromas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.uao.GrandeAromas.Repository.IShoppingCartRepository;
import com.uao.GrandeAromas.Domain.Consulta1DTO;
import com.uao.GrandeAromas.Domain.Consulta2DTO;
import com.uao.GrandeAromas.Domain.ShoppingCartDTO;
import com.uao.GrandeAromas.Model.DetailShoppingCartModel;
import com.uao.GrandeAromas.Model.ShoppingCartModel;
import com.uao.GrandeAromas.Model.UsuariosModel;

@Service
public class ShoppingCartServiceImp implements IShoppingCartService{

    @Autowired
    @Lazy
    IDetailShoppingCartService detailShoppingCartService;

    @Autowired
    IShoppingCartRepository shoppingCartRepository;

    @Autowired
    IUsuarioService usuarioService;

    

    @Override
    public String guardarShoppingCart(ShoppingCartModel shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
        return "El carrito de compras fue creado exitosamente";
    }

    @Override
    public List<ShoppingCartModel> obtenerShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    @Override

    public Optional<ShoppingCartModel> obtenerShoppingCartPorId(int id) {
        return shoppingCartRepository.findById(id);
    }
    
    @Override

    public String actualizarShoppingCartPorId(ShoppingCartModel shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
        return "El carrito de compras con id se actualizo correctamente";
    }

    @Override
    public String eliminarShoppingCartPorId(int id) {
        shoppingCartRepository.deleteById(id);
        return "El carrito de compras con id " + id + " fue eliminado correctamente";
    }

    @Override
    public List<ShoppingCartDTO> obtenerVentasdelDia(Date fechaVenta) {
        return shoppingCartRepository.obtenerVentasdelDia(fechaVenta);
    }

    @Override
    public List<Consulta2DTO> obtenerVentas(Date fechaVenta) {
        List<ShoppingCartDTO> ventasDelDia = shoppingCartRepository.obtenerVentasdelDia(fechaVenta);
        List<Consulta2DTO> resultados = new ArrayList<>();

        for (ShoppingCartDTO venta : ventasDelDia) {
            Consulta2DTO consulta2dto = new Consulta2DTO();
            consulta2dto.setCodigoVenta(venta.getId());
            consulta2dto.setTotalCompra(venta.getTotalPrice());
            
            UsuariosModel usuario = usuarioService.encontrarIdyUsuarioNombre(venta.getUserId());
            consulta2dto.setNombreCliente(usuario.getNameUser());

            List<DetailShoppingCartModel> detallesVenta = detailShoppingCartService.obtenerDetallesPorCarrito(venta.getId());

            List<Map<String, Integer>> detallesMapeados = new ArrayList<>();
            for (DetailShoppingCartModel detalle : detallesVenta) {
                Map<String, Integer> detalleMap = new HashMap<>();
                detalleMap.put("productId", detalle.getProductId());
                detalleMap.put("quantity", detalle.getQuantity());
                detallesMapeados.add(detalleMap);
            }
            consulta2dto.setDetalleVenta(detallesMapeados);
            resultados.add(consulta2dto);
              
        }
        return resultados;
    }

    @Override
    public List<Consulta1DTO> obtenerShoppingCartPorUserId(int userId) {
        List<ShoppingCartDTO> shoppingCarts = shoppingCartRepository.findByUserId(userId);
        List<Consulta1DTO> resultados = new ArrayList<>();

        for (ShoppingCartDTO shoppingCart : shoppingCarts) {
            Consulta1DTO consulta1dto = new Consulta1DTO();
            consulta1dto.setUserId(shoppingCart.getUserId());
            consulta1dto.setAddressInfo(shoppingCart.getAddressInfo());
            consulta1dto.setDate(shoppingCart.getDate());
            consulta1dto.setTotalPrice(shoppingCart.getTotalPrice());
            consulta1dto.setOrderStatus(shoppingCart.getOrderStatus());

            List<DetailShoppingCartModel> detallesVenta = detailShoppingCartService.obtenerDetallesPorCarrito(shoppingCart.getId());

            List<Map<String, Integer>> detallesMapeados = new ArrayList<>();
            for (DetailShoppingCartModel detalle : detallesVenta) {
                Map<String, Integer> detalleMap = new HashMap<>();
                detalleMap.put("productId", detalle.getProductId());
                detalleMap.put("quantity", detalle.getQuantity());
                detallesMapeados.add(detalleMap);
            }
            consulta1dto.setDetalleVenta(detallesMapeados);
            resultados.add(consulta1dto);
        }
        return resultados;
       
    }
} 
      
