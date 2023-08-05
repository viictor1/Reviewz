package com.Reviewz.core.user.utils;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

import java.util.UUID;

public class UserUtils {

    private static TokenService tokenService;
    private static UserGateway userGateway;

    public UserUtils(TokenService tokenService, UserGateway userGateway){
        this.tokenService = tokenService;
        this.userGateway = userGateway;
    }

    public static UserSchema retrieveUserFromToken(String token) throws ValidationError {
        String login = tokenService.validateToken(token.replace("Bearer ", ""));

        return userGateway.findOptionalByLogin(login)
                .orElseThrow(() -> new ValidationError("User not found"));
    }

    public static String getLoginByToken(String token){
        return tokenService.validateToken(token.replace("Bearer ", ""));
    }

    public static UUID getUserIdByLogin(String login) throws ValidationError {
        UserSchema user = getUserByLogin(login);
        return user.getId();
    }

    public static UserSchema getUserByLogin(String login) throws ValidationError {
        return userGateway.findOptionalByLogin(login)
                .orElseThrow(() -> new ValidationError("User not found"));
    }
}
