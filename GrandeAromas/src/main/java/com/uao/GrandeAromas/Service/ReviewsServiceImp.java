package com.uao.GrandeAromas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uao.GrandeAromas.Model.ReviewsModel;
import com.uao.GrandeAromas.Repository.IReviewsRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImp implements IReviewsService {
    @Autowired
    IReviewsRepository reviewsRepository;

    @Override
    public String guardarReview(ReviewsModel review) {
        reviewsRepository.save(review);
        return "La review realizada por "+ review.getNameUser() +" fue creada exitosamente";
    }

    @Override
    public List <ReviewsModel> listarReviews (){
        return reviewsRepository.findAll();

    }

    @Override
    public String eliminarReviewPorId(int reviewId) {
        reviewsRepository.deleteById(reviewId);
        return "La review con id "+ reviewId +" fue eliminada exitosamente";
    }

    @Override
    public String actualizarReview(ReviewsModel review) {
        reviewsRepository.save(review);
        return "La review con id "+ review.getId() +" fue actualizada exitosamente";
    }
    @Override
    public Optional<ReviewsModel> obtenerReviewsPorId(int reviewId) {
        return reviewsRepository.findById(reviewId);
    }

}    

