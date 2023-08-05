package com.Reviewz.core.review.usecase;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

import static com.Reviewz.core.user.utils.UserUtils.getIdByToken;

@Service
public class DeleteReviewUseCase {
    private final ReviewGateway reviewGateway;

    public DeleteReviewUseCase(ReviewGateway reviewGateway){
        this.reviewGateway = reviewGateway;
    }

    public void execute(Input input) throws AccessDeniedException, ValidationError {
        ReviewSchema review = new ReviewSchema(reviewGateway.getReviewById(input.reviewId));
        var userId = getIdByToken(input.token);

        if(review.getUser().getId() != userId){
            throw new AccessDeniedException("Cannot access another users review");
        }

        reviewGateway.deleteReview(review);

    }

    public record Input(Long reviewId, String token){}
}
