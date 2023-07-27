package com.Reviewz.core.user.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.infra.dataprovider.database.UserRepository;
import com.Reviewz.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

class UpdateAccountUseCaseTest {
    MockUser input;

    @InjectMocks
    private UpdateAccountUseCase updateAccountUseCase;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserGateway userGateway;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        input = new MockUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void userNotFoundException() {
        when(tokenService.validateToken("token")).thenReturn("test");
        lenient().when(userRepository.findOptionalByLogin("test")).thenReturn(null);

        var input = new UpdateAccountUseCase.Input("name", "123", "123", "token");

        Exception exception = assertThrows(ValidationError.class, () -> updateAccountUseCase.execute(input));
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}