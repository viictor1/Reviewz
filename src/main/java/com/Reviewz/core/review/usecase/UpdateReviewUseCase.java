package com.Reviewz.core.review.usecase;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import static com.Reviewz.core.user.utils.UserUtils.getIdByToken;
import static com.Reviewz.core.user.utils.UserUtils.retrieveUserFromToken;

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

        Review updatedReview = setReviewFromInput(input);
        reviewGateway.save(new ReviewSchema(updatedReview));
    }

    public Review setReviewFromInput(Input input) throws ValidationError {
        Review review = new Review();
        review.setTitle(input.title);
        review.setMadeBy(input.madeBy);
        review.setCategory(input.category);
        review.setStars(input.stars);
        review.setReview(input.review);
        review.setPublishedAt(input.publishedAt);
        review.setReviewedAt(new Date());
        review.setUser(retrieveUserFromToken(input.userToken));
        return review;
    }

    public record Input(Long id, String title, String madeBy, String category, int stars, String review, Date publishedAt, String userToken){}

}
