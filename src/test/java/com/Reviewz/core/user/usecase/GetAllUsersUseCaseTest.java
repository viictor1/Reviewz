package com.Reviewz.core.user.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.infra.dataprovider.database.UserRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class GetAllUsersUseCaseTest {
    MockUser input;

    @InjectMocks
    private GetAllUsersUseCase getAllUsersUseCase;

    @Mock
    private UserGateway userGateway;

    @BeforeEach
    void setUp() {
        input = new MockUser();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void execute() {
        List<UserSchema> userList = input.mockEntityList();

        when(userGateway.findAll()).thenReturn(userList);

        var result = getAllUsersUseCase.execute();

        var userOne = result.get(1);

        assertNotNull(result);
        assertEquals(14, result.size());

        assertEquals("Name Test 1", userOne.getName());
        assertEquals("Login Test 1", userOne.getLogin());
        assertEquals("Password Test 1", userOne.getPassword());
        assertEquals(UserRole.USER, userOne.getRole());

        var userFive = result.get(5);

        assertEquals("Name Test 5", userFive.getName());
        assertEquals("Login Test 5", userFive.getLogin());
        assertEquals("Password Test 5", userFive.getPassword());
        assertEquals(UserRole.USER, userFive.getRole());

        var userTen = result.get(10);

        assertEquals("Name Test 10", userTen.getName());
        assertEquals("Login Test 10", userTen.getLogin());
        assertEquals("Password Test 10", userTen.getPassword());
        assertEquals(UserRole.USER, userTen.getRole());
    }
}
