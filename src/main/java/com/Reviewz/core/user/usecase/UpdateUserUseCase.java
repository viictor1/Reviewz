package com.Reviewz.core.user.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.infra.dataprovider.schema.user.UserRole;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {

    private final UserGateway userGateway;
    private final TokenService tokenService;
    private PasswordEncoder passwordEncoder;

    public UpdateUserUseCase(UserGateway userGateway, TokenService tokenService, PasswordEncoder passwordEncoder){
        this.userGateway = userGateway;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(Input input, String token) throws ValidationError {
        UserSchema userSchema = retrieveUserFromToken(token);

        if(typedPasswordEqualsActualPassword(input.oldPassword, userSchema.getPassword())){
            updateUserWhereInputNotNull(userSchema, input);
        }

        userGateway.update(userSchema);
    }

    private void updateUserWhereInputNotNull(UserSchema userSchema, Input input){
        if(input.name != null){
            userSchema.setName(input.name);
        }
        if (input.newPassword != null){
            userSchema.setPassword(input.newPassword);
        }
    }


    private UserSchema retrieveUserFromToken(String token) throws ValidationError {
        String login = tokenService.validateToken(token);

        return userGateway.findOptionalByLogin(login)
                .orElseThrow(() -> new ValidationError("User not found"));
    }

    private boolean typedPasswordEqualsActualPassword(String password, String userPassword) {
        return passwordEncoder.matches(password, userPassword);
    }

    private record Input(
            String name,
            String oldPassword,
            String newPassword
    ){}

}
