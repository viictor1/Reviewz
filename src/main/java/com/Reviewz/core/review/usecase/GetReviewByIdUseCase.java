package com.Reviewz.core.review.usecase;

import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
public class GetReviewByIdUseCase {
    private final ReviewGateway reviewGateway;

    public GetReviewByIdUseCase(ReviewGateway reviewGateway){
        this.reviewGateway = reviewGateway;
    }

    public Review execute(Input input) throws AccessDeniedException {
        var review = reviewGateway.getReviewById(input.reviewId);
        if(review.getUser().getId() != input.userId){
            throw new AccessDeniedException("Cannot access another users review");
        }

        return review;
    }

    public record Input(Long reviewId, UUID userId){}

}
