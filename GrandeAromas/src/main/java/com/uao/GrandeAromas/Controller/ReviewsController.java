package com.uao.GrandeAromas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uao.GrandeAromas.Model.ReviewsModel;
import com.uao.GrandeAromas.Model.UsuariosModel;
import com.uao.GrandeAromas.Service.IReviewsService;
import com.uao.GrandeAromas.Service.IUsuarioService;

@RestController
@RequestMapping("/GrandeAromas/reviews")
public class ReviewsController {
    @Autowired
    IReviewsService reviewsService;

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/crearReview")
    public ResponseEntity<String> crearReview(@RequestBody ReviewsModel review) {
        UsuariosModel usuario = usuarioService.encontrarIdyUsuarioNombre(review.getUserId());
        review.setUserName(usuario.getNameUser());
        reviewsService.guardarReview(review);
        return new ResponseEntity<String>(reviewsService.guardarReview(review), HttpStatus.OK);
    }

    @GetMapping("/listarReviews")
    public ResponseEntity<List<ReviewsModel>> listarReviews() {
        return new ResponseEntity<List<ReviewsModel>>(reviewsService.listarReviews(), HttpStatus.OK);
    }
}
