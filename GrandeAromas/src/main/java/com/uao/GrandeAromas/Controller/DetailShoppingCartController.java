package com.uao.GrandeAromas.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.uao.GrandeAromas.Service.IDetailShoppingCartService;
import com.uao.GrandeAromas.Model.DetailShoppingCartModel;



@RestController
@RequestMapping("/GrandeAromas/detailShoppingCart")
public class DetailShoppingCartController {

    @Autowired
    IDetailShoppingCartService detailShoppingCartService;
    

    @GetMapping("/listarDetailShoppingCarts")
    public ResponseEntity<List<DetailShoppingCartModel>> listarDetailShoppingCarts() {
        return new ResponseEntity<List<DetailShoppingCartModel>>(detailShoppingCartService.obtenerDetailShoppingCarts(), HttpStatus.OK);
    }

}
