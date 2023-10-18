package com.uao.GrandeAromas.Service;
import java.util.List;
import com.uao.GrandeAromas.Model.ReviewsModel;

public interface IReviewsService {

    String guardarReview(ReviewsModel Reviews);
    List<ReviewsModel> listarReviews();
    //métodos faltantes: buscarIdcomentario, eliminar, actualizar
    
}