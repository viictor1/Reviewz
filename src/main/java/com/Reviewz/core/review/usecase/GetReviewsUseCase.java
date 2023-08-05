package com.Reviewz.core.review.usecase;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.Reviewz.core.user.utils.UserUtils.getLoginByToken;
import static com.Reviewz.core.user.utils.UserUtils.getUserIdByLogin;

@Service
public class GetReviewsUseCase {
    private final ReviewGateway reviewGateway;

    public GetReviewsUseCase(ReviewGateway reviewGateway){
        this.reviewGateway = reviewGateway;
    }

    public List<Review> getReviewsByToken(String token) throws ValidationError {
        String login = getLoginByToken(token);
        UUID userId = getUserIdByLogin(login);

        return reviewGateway.getReviewsByUserId(userId);
    }




}
