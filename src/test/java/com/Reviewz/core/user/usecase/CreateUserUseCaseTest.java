package com.Reviewz.core.user.usecase;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.LoginAlreadyExistsException;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import com.Reviewz.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CreateUserUseCaseTest {

    MockUser input;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

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
    void shouldReturnLoginException() throws Exception {
        Optional<UserSchema> user = Optional.of(input.mockEntity(1));

        when(userGateway.findOptionalByLogin("test")).thenReturn(user);

        var create = new CreateUserUseCase.Input("test", "test", "test");

        Exception exception = assertThrows(LoginAlreadyExistsException.class, () -> createUserUseCase.execute(create));
        String expectedMessage = "Login already being used";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void shouldCreateUser() throws Exception {
        when(userGateway.findOptionalByLogin("test")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("test")).thenReturn("encoded");

        var create = new CreateUserUseCase.Input("test", "test", "test");
        assertDoesNotThrow(() -> createUserUseCase.execute(create));
    }
}