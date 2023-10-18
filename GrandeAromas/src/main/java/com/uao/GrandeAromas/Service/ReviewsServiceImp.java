package com.uao.GrandeAromas.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uao.GrandeAromas.Model.ReviewsModel;
import com.uao.GrandeAromas.Repository.IReviewsRepository;
import java.util.List;

public class ReviewsServiceImp implements IReviewsService{
    @Autowired
    IReviewsRepository reviewsRepository;

    @Override
    public String guardarReview(ReviewsModel review) {
        reviewsRepository.save(review);
        return "La review realizada por "+ review.getUserName() +" fue creada exitosamente";
    }

    @Override
    public List <ReviewsModel> listarReviews (){
        return reviewsRepository.findAll();

    }

}    

