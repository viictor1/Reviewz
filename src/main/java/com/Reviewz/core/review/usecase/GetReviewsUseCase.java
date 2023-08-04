package com.Reviewz.core.review.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetReviewsUseCase {
    private final TokenService tokenService;
    private final ReviewGateway reviewGateway;

    private final UserGateway userGateway;

    public GetReviewsUseCase(TokenService tokenService, ReviewGateway reviewGateway, UserGateway userGateway){
        this.tokenService = tokenService;
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
    }

    public List<Review> getReviewsByToken(String token) throws ValidationError {
        String login = getLoginByToken(token);
        UUID userId = getUserIdByLogin(login);

        return reviewGateway.getReviewsByUserId(userId);
    }

    private String getLoginByToken(String token){
        return tokenService.validateToken(token);
    }

    private UUID getUserIdByLogin(String login) throws ValidationError {
        UserSchema user = getUserByLogin(login);
        return user.getId();
    }

    private UserSchema getUserByLogin(String login) throws ValidationError {
        return userGateway.findOptionalByLogin(login)
                .orElseThrow(() -> new ValidationError("User not found"));
    }


}
