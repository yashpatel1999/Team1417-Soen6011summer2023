package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.UserLogin;
import com.application.careerserviceapplication.repositories.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavedLoginCredentials() {
        // Given
        String userid = "testUser";
        String pwd = "testPassword";
        String type = "C";

        // When
        loginService.savedLoginCredentials(userid, pwd, type);

        // Then
        verify(loginRepository, times(1)).save(any(UserLogin.class));
    }

    @Test
    public void testGetLoginbyUser() {
        // Given
        UserLogin mockUserLogin = new UserLogin();
        mockUserLogin.setUid("testUser");
        mockUserLogin.setPassword("encodedPassword");
        mockUserLogin.setType("C");
        when(loginRepository.getUserLoginByuid("testUser")).thenReturn(mockUserLogin);

        // When
        UserLogin result = loginService.getLoginbyUser(mockUserLogin);

        // Then
        assertEquals("testUser", result.getUid());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals("C", result.getType());
    }
}
