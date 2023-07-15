package com.Reviewz.entrypoint.user;

import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.core.user.usecase.UpdateAccountUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UpdateAccountController {

    private UpdateAccountUseCase updateAccountUseCase;

    public UpdateAccountController(UpdateAccountUseCase updateAccountUseCase){
        this.updateAccountUseCase = updateAccountUseCase;
    }


    @PutMapping
    @SecurityRequirement(name = "bearerAuth")
    public void updateAccount(@RequestHeader(name="Authorization") String token, Request request) throws ValidationError {
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
