package com.Reviewz.core.review.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.genericException.ValidationError;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.stereotype.Service;

import java.sql.Date;

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

    public void execute(Input input){

    }

    public Review setReviewFromInput(Input input) throws ValidationError {
        Review review = new Review();
        review.setTitle(input.title);
        review.setGenre(input.genre);
        review.setStars(input.stars);
        review.setReview(input.review);
        review.setPublishedAt(input.publishedAt);
        review.setUser(getUserFromToken(input.userToken));
        return review;
    }

    public UserSchema getUserFromToken(String token) throws ValidationError {
        String login = tokenService.validateToken(token);

        return userGateway.findOptionalByLogin(login)
                .orElseThrow(() -> new ValidationError("User not found"));
    }

    public record Input(String title, String genre, int stars, String review, Date publishedAt, String userToken){}

}
