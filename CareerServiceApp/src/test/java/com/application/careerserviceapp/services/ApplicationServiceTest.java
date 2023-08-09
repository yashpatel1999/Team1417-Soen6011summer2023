package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.Application;
import com.application.careerserviceapplication.repositories.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@DataJpaTest
public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveApplication() {
        // Given
        Application mockApplication = new Application();
        // Set up any required properties on the mockApplication object

        // When
        applicationService.saveApplication(mockApplication);

        // Then
        verify(applicationRepository, times(1)).save(mockApplication);
    }
}
