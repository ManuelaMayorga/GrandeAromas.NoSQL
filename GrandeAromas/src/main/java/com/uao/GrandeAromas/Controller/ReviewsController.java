package com.uao.GrandeAromas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uao.GrandeAromas.Exceptions.RecursoNoEncontradoException;
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
    public ResponseEntity<String> guardarReview(@RequestBody ReviewsModel review) {
        UsuariosModel usuario = usuarioService.encontrarIdyUsuarioNombre(review.getUserId());
        review.setNameUser(usuario.getNameUser());
        reviewsService.guardarReview(review);
        return new ResponseEntity<String>(reviewsService.guardarReview(review), HttpStatus.OK);
    }

    @GetMapping("/listarReviews")
    public ResponseEntity<List<ReviewsModel>> listarReviews() {
        return new ResponseEntity<List<ReviewsModel>>(reviewsService.listarReviews(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{reviewId}")
    public ResponseEntity<String> eliminarReviewPorId(@PathVariable int reviewId) {
        Optional<ReviewsModel> optionalReview = reviewsService.obtenerReviewsPorId(reviewId);

        if (!optionalReview.isPresent()) {
            return new ResponseEntity<String>("Review no Encontrada", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>(reviewsService.eliminarReviewPorId(reviewId), HttpStatus.OK);
    }
    
    @PutMapping("/actualizar/{reviewId}")
    public ResponseEntity<String> actualizarReviewPorId(@PathVariable int reviewId, @RequestBody ReviewsModel reviewDetail) {
    ReviewsModel review = reviewsService.obtenerReviewsPorId(reviewId).orElseThrow(() -> new RecursoNoEncontradoException("Review no encontrada con el id: " + reviewId));

        review.setComment(reviewDetail.getComment());
        review.setCalification(reviewDetail.getCalification());

        if (!reviewDetail.getComment().isEmpty() || reviewDetail.getCalification() != 0) {
            reviewsService.actualizarReview(review);
            return new ResponseEntity<String>(reviewsService.actualizarReview(review),HttpStatus.OK);
        } 

        else if (reviewDetail.getCalification() <0 || reviewDetail.getCalification() >5) {
            return new ResponseEntity<String>("La calificación debe estar entre 0 y 5", HttpStatus.BAD_REQUEST);
        }

        else {
            throw new RecursoNoEncontradoException("No se puede actualizar la review con el id: " + reviewId + " porque no se enviaron todos los datos");
        }
    }
    
    @GetMapping("/buscarReview/{reviewId}")
    public ResponseEntity<?> obtenerReviewsPorId(@PathVariable int reviewId) {
        Optional<ReviewsModel> optionalReview = reviewsService.obtenerReviewsPorId(reviewId);

        if (!optionalReview.isPresent()) {
            return new ResponseEntity<>("Revisión no encontrada para el ID: " + reviewId, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalReview.get(), HttpStatus.OK);
    }

    

        
}
