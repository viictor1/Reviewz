package com.Reviewz.core.review.contract;

import com.Reviewz.core.review.model.Review;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;

import java.util.List;
import java.util.UUID;

public interface ReviewGateway {
    void save(ReviewSchema reviewSchema);

    List<Review> getReviewsByUserId(UUID userId);

    ReviewSchema getReviewById(Long id);

    void deleteReview(ReviewSchema reviewSchema);
}
