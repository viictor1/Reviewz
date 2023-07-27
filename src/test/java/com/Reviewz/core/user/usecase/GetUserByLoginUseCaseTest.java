package com.Reviewz.core.user.usecase;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.infra.dataprovider.schema.user.UserRole;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import com.Reviewz.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class GetUserByLoginUseCaseTest {

    MockUser input;

    @InjectMocks
    private GetUserByLoginUseCase getUserByLoginUseCase;

    @Mock
    private UserGateway userGateway;

    @BeforeEach
    void setUp() {
        input = new MockUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() throws ValidationError {
        UserSchema user = input.mockEntity(1);

        when(userGateway.findOptionalByLogin(user.getLogin())).thenReturn(Optional.of(user));

        var result = getUserByLoginUseCase.execute(user.getLogin());

        assertNotNull(result);

        assertEquals("Name Test 1", result.getName());
        assertEquals("Login Test 1", result.getLogin());
        assertEquals("Password Test 1", result.getPassword());
        assertEquals(UserRole.USER, result.getRole());
    }

    @Test
    void userNotFoundException() throws ValidationError {
        UserSchema user = input.mockEntity(1);

        lenient().when(userGateway.findOptionalByLogin(user.getLogin())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ValidationError.class,
                () -> getUserByLoginUseCase.execute("Incorrect Login"));
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}