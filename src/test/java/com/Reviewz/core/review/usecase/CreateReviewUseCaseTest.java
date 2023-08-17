package com.Reviewz.core.review.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.contract.ReviewGateway;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.utils.UserUtils;
import com.Reviewz.infra.dataprovider.database.user.UserRepository;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import com.Reviewz.mocks.MockReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

public class CreateReviewUseCaseTest {

    MockReview mock;
    @InjectMocks
    private CreateReviewUseCase createReviewUseCase;

    @Mock
    private ReviewGateway reviewGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldThrowValidationError() {
        CreateReviewUseCase.Input input = new CreateReviewUseCase.Input(
                null, "John Doe", "Movies", 4, "This is a test review.",
                new Date(), "userToken"
        );

        assertThrows(ValidationError.class, () -> createReviewUseCase.execute(input));
    }
}
