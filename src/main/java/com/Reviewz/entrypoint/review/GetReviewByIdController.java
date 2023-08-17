package com.Reviewz.entrypoint.review;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.core.review.usecase.GetReviewByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@RestController
@RequestMapping("/review")
@Tag(name = "Review", description = "Endpoints for Managing Reviews")
public class GetReviewByIdController {

    private GetReviewByIdUseCase getReviewByIdUseCase;

    public GetReviewByIdController(GetReviewByIdUseCase getReviewByIdUseCase) {
        this.getReviewByIdUseCase = getReviewByIdUseCase;
    }

    @GetMapping(value = "/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Gets a review by id",
            description = "Gets a review by id",
            tags = {"Review"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "User isn't authenticated", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Access denied", responseCode = "403", content = @Content),
            }
    )
    public Response getUserById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) throws ValidationError, AccessDeniedException {
        Review review = getReviewByIdUseCase.execute(new GetReviewByIdUseCase.Input(id, token));

        return new Response(
                    review.getId(),
                    review.getTitle(),
                    review.getMadeBy(),
                    review.getCategory(),
                    review.getStars(),
                    review.getReview(),
                    review.getPublishedAt(),
                    review.getReviewedAt()
            );
    }

    private record Response(
            Long id,
            String title,
            String madeBy,
            String category,
            int stars,
            String review,
            Date publishedAt,
            Date reviewedAt
    ) {}

}

