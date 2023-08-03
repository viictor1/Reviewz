package com.Reviewz.entrypoint.user;

import com.Reviewz.core.user.genericException.ValidationError;
import com.Reviewz.core.user.usecase.UpdateAccountUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
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
    @Operation(summary = "Updates an account",
            description = "Updates account by passing the jwt token " +
                    "in the request header and by passing name, oldPassword and newPassword" +
                    "oldPassword cannot be blank, if the other params are blank that attribute will not be changed",
            tags = {"User"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "User not found or incorrect password", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Missing password", responseCode = "422", content = @Content)

            }
    )
    public void updateAccount(@RequestHeader("Authorization") String token, @RequestBody Request request) throws ValidationError {

        updateAccountUseCase.execute(new UpdateAccountUseCase.Input(
                request.name,
                request.oldPassword,
                request.newPassword,
                token
        ));
    }

    @Schema(hidden = true, name = "Update Request")
    private record Request(
            String name,
            @NotBlank String oldPassword,
            String newPassword
    ){}
}
