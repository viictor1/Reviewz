package com.Reviewz.infra.dataprovider.database.review;

import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<Review> getReviewsByUserId(UUID userId) {
        List<ReviewSchema> schemaList = reviewRepository.getReviewsByUserId(userId);
        List<Review> reviewList = new ArrayList<>();

        for (ReviewSchema schema: schemaList) {
            reviewList.add(new Review(schema));
        }

        return reviewList;
    }

    @Override
    public ReviewSchema getReviewById(Long id){
        ReviewSchema schema = reviewRepository.getReferenceById(id);
        return schema;
    }

    @Override
    public void deleteReview(ReviewSchema reviewSchema){
        reviewRepository.delete(reviewSchema);
    }
}
