package com.Reviewz.core.review.usecase;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

import static com.Reviewz.core.user.utils.UserUtils.getIdByToken;
import static com.Reviewz.core.user.utils.UserUtils.retrieveUserFromToken;

@Service
public class GetReviewByIdUseCase {
    private final ReviewGateway reviewGateway;

    public GetReviewByIdUseCase(ReviewGateway reviewGateway){
        this.reviewGateway = reviewGateway;
    }

    public Review execute(Input input) throws AccessDeniedException, ValidationError {
        var review = reviewGateway.getReviewById(input.reviewId);
        var userId = getIdByToken(input.token);

        if(review.getUser().getId() != userId){
            throw new AccessDeniedException("Cannot access another users review");
        }

        return review;
    }

    public record Input(Long reviewId, String token){}

}
