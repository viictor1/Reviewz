package com.Reviewz.entrypoint.review;

import com.Reviewz.core.review.usecase.CreateReviewUseCase;
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
public class CreateReviewController {

    private CreateReviewUseCase createReviewUseCase;

    public CreateReviewController(CreateReviewUseCase createReviewUseCase) {
        this.createReviewUseCase = createReviewUseCase;
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Adds a review",
            description = "Add a review",
            tags = {"Review"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Incorrect value", responseCode = "422", content = @Content)
            }
    )
    public void createUser(@RequestHeader("Authorization") String token, @RequestBody Request request) throws Exception {
        createReviewUseCase.execute(
                new CreateReviewUseCase.Input(
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

    @Schema(hidden = true, name = "Create Review Request")
    public record Request(
            @NotBlank String title,
            @NotBlank String category,
            String madeBy,
            @NotBlank int stars,
            String review,
            Date publishedAt
    ){}

}
