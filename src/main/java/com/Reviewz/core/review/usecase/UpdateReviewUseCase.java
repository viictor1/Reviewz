package com.Reviewz.core.review.usecase;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import static com.Reviewz.core.user.utils.UserUtils.getIdByToken;

@Service
public class UpdateReviewUseCase {

    private final ReviewGateway reviewGateway;

    public UpdateReviewUseCase(ReviewGateway reviewGateway){
        this.reviewGateway = reviewGateway;
    }

    public void execute(Input input) throws AccessDeniedException, ValidationError {
        var oldReview = new Review(reviewGateway.getReviewById(input.id));
        var userId = getIdByToken(input.userToken);

        if(oldReview.getUser().getId() != userId){
            throw new AccessDeniedException("Cannot access another users review");
        }

        var updatedReview = updateReviewFromInput(oldReview, input);
        reviewGateway.save(new ReviewSchema(updatedReview));
    }

    public Review updateReviewFromInput(Review review, Input input) throws ValidationError {
        if (input.title != null) {
            review.setTitle(input.title);
        }
        if (input.madeBy != null) {
            review.setMadeBy(input.madeBy);
        }
        if (input.category != null) {
            review.setCategory(input.category);
        }
        if (input.stars != review.getStars()) {
            review.setStars(input.stars);
        }
        if (input.review != null) {
            review.setReview(input.review);
        }
        if (input.publishedAt != null) {
            review.setPublishedAt(input.publishedAt);
        }

        return review;
    }

    public record Input(Long id, String title, String madeBy, String category, int stars, String review, Date publishedAt, String userToken){}

}
