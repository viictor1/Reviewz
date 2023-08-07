package com.Reviewz.entrypoint.review;

import com.Reviewz.core.review.usecase.UpdateReviewUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/review")
@Tag(name = "Review", description = "Endpoints for Managing Reviews")
public class UpdateReviewController {

    private UpdateReviewUseCase updateReviewUseCase;

    public UpdateReviewController(UpdateReviewUseCase updateReviewUseCase) {
        this.updateReviewUseCase = updateReviewUseCase;
    }

    @PutMapping
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates a review",
            description = "Updates a review",
            tags = {"Review"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Incorrect value", responseCode = "422", content = @Content),
                    @ApiResponse(description = "Access denied", responseCode = "403", content = @Content)
            }
    )
    public void updateUser(@RequestHeader("Authorization") String token, @RequestBody Request request) throws Exception {
        updateReviewUseCase.execute(
                new UpdateReviewUseCase.Input(
                        request.id,
                        request.title,
                        request.madeBy,
                        request.category,
                        request.stars,
                        request.review,
                        request.publishedAt,
                        token
                )
        );
    }

    @Schema(hidden = true, name = "Update Review Request")
    public record Request(
            @NotBlank Long id,
            @NotBlank String title,
            @NotBlank String category,
            String madeBy,
            @NotBlank int stars,
            String review,
            Date publishedAt
    ){}
}
