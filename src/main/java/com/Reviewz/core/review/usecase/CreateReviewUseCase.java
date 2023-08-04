package com.Reviewz.core.review.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateReviewUseCase {

    private final ReviewGateway reviewGateway;
    private final UserGateway userGateway;
    private final TokenService tokenService;

    public CreateReviewUseCase(ReviewGateway reviewGateway, UserGateway userGateway, TokenService tokenService){
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.tokenService = tokenService;
    }

    public void execute(Input input) throws ValidationError {
        Review review = setReviewFromInput(input);
        reviewGateway.create(new ReviewSchema(review));
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
        review.setUser(getUserFromToken(input.userToken));
        return review;
    }

    public UserSchema getUserFromToken(String token) throws ValidationError {
        String login = tokenService.validateToken(token.replace("Bearer ", ""));

        return userGateway.findOptionalByLogin(login)
                .orElseThrow(() -> new ValidationError("User not found"));
    }

    public record Input(String title, String madeBy, String category, int stars, String review, Date publishedAt, String userToken){}

}
