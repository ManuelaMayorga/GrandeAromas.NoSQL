package com.uao.GrandeAromas.Repository;
import com.uao.GrandeAromas.Model.ReviewsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IReviewsRepository extends MongoRepository<ReviewsModel,Integer>{
}


