package com.Reviewz.entrypoint.user;

import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.core.user.usecase.UpdateAccountUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for Managing Users/Account")
public class UpdateAccountController {

    private UpdateAccountUseCase updateAccountUseCase;

    public UpdateAccountController(UpdateAccountUseCase updateAccountUseCase){
        this.updateAccountUseCase = updateAccountUseCase;
    }


    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "bearerAuth")
    public void updateAccount(@RequestHeader("Authorization") String token, @RequestBody Request request) throws ValidationError {

        updateAccountUseCase.execute(new UpdateAccountUseCase.Input(
                request.name,
                request.oldPassword,
                request.newPassword,
                token
        ));
    }

    private record Request(
            String name,
            String oldPassword,
            String newPassword
    ){}
}
