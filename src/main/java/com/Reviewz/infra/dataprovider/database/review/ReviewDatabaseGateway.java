package com.Reviewz.infra.dataprovider.database.review;

import com.Reviewz.core.review.model.contract.ReviewGateway;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import org.springframework.stereotype.Component;

@Component
public class ReviewDatabaseGateway implements ReviewGateway {

    private ReviewRepository reviewRepository;

    public ReviewDatabaseGateway(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void create(ReviewSchema reviewSchema) {
        reviewRepository.save(reviewSchema);
    }
}
