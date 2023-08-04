package com.Reviewz.core.user.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateAccountUseCase {

    private final UserGateway userGateway;
    private final TokenService tokenService;
    private PasswordEncoder passwordEncoder;

    public UpdateAccountUseCase(UserGateway userGateway, TokenService tokenService, PasswordEncoder passwordEncoder){
        this.userGateway = userGateway;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void execute(Input input) throws ValidationError {
        UserSchema userSchema = retrieveUserFromToken(input.token.replace("Bearer ", ""));
        if(typedPasswordEqualsActualPassword(input.oldPassword, userSchema.getPassword())) {
            updateUserWhereInputNotNull(userSchema, input);
            userGateway.update(userSchema);
        }
        else{
            throw new ValidationError("Incorrect password");
        }
    }

    private void updateUserWhereInputNotNull(UserSchema userSchema, Input input){
        if(input.name != null){
            userSchema.setName(input.name);
        }
        if (input.newPassword != null){
            userSchema.setPassword(passwordEncoder.encode(input.newPassword));
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

    public record Input(
            String name,
            String oldPassword,
            String newPassword,
            String token
    ){}

}
