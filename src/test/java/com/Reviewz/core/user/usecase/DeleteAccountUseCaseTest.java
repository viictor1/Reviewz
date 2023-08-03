package com.Reviewz.core.user.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.infra.dataprovider.database.user.UserRepository;
import com.Reviewz.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class DeleteAccountUseCaseTest {

    MockUser input;

    @InjectMocks
    private DeleteAccountUseCase deleteAccountUseCase;

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

        var input = new DeleteAccountUseCase.Input("token", "123");

        Exception exception = assertThrows(ValidationError.class, () -> deleteAccountUseCase.execute(input));
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}