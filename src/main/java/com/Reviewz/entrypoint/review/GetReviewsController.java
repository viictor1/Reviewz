package com.Reviewz.entrypoint.review;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.core.review.usecase.GetReviewsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/review")
@Tag(name = "Review", description = "Endpoints for Managing Reviews")
public class GetReviewsController {

    private GetReviewsUseCase getReviewsUseCase;

    public GetReviewsController(GetReviewsUseCase getReviewsUseCase) {
        this.getReviewsUseCase = getReviewsUseCase;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Gets all of the authenticated user's reviews",
            description = "Gets all of the authenticated user's reviews",
            tags = {"Review"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "User isn't authenticated", responseCode = "403", content = @Content)
            }
    )
    public List<Response> getAllUsers(@RequestHeader("Authorization") String token) throws ValidationError {
        List<Review> reviewList = getReviewsUseCase.getReviewsByToken(token);
        List<Response> responseList = new ArrayList<Response>();

        for (Review review : reviewList) {
            responseList.add(new Response(
                    review.getId(),
                    review.getTitle(),
                    review.getMadeBy(),
                    review.getCategory(),
                    review.getStars(),
                    review.getReview(),
                    review.getPublishedAt(),
                    review.getReviewedAt()
            ));
        }

        return responseList;
    }

    record Response(
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
