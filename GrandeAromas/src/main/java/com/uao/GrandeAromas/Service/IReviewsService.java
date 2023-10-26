package com.uao.GrandeAromas.Service;
import java.util.List;
import java.util.Optional;

import com.uao.GrandeAromas.Model.ReviewsModel;


public interface IReviewsService {

    String guardarReview(ReviewsModel Reviews);
    
    List<ReviewsModel> listarReviews();
    
    String actualizarReviewPorId(ReviewsModel Reviews);

    String eliminarReviewPorId(int reviewId);

    Optional<ReviewsModel> obtenerReviewsPorId(int reviewId);

    
}