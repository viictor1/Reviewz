package com.Reviewz.entrypoint.review;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.core.review.usecase.DeleteReviewUseCase;
import com.Reviewz.core.review.usecase.GetReviewByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/review")
@Tag(name = "Review", description = "Endpoints for Managing Reviews")
public class DeleteReviewController {

    private DeleteReviewUseCase deleteReviewUseCase;

    public DeleteReviewController(DeleteReviewUseCase deleteReviewUseCase){
        this.deleteReviewUseCase = deleteReviewUseCase;
    }

    @DeleteMapping(value = "/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a review by id",
            description = "Deletes a review by id",
            tags = {"Review"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "204", content = @Content),
                    @ApiResponse(description = "User isn't authenticated", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Access denied", responseCode = "403", content = @Content),
            }
    )
    public void getAllUsers(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) throws ValidationError, AccessDeniedException {
        deleteReviewUseCase.execute(new DeleteReviewUseCase.Input(id, token));
    }
}
