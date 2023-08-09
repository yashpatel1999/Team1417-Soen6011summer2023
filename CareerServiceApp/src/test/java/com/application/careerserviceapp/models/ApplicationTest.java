package com.application.careerserviceapplication.models;

import com.application.careerserviceapplication.models.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApplicationTest {

    @Mock
    private EntityManager entityManager;

    private Application application;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        application = new Application();
        application.setA_id(1L);
        application.setStatus("Pending");
        application.setC_id("company123");
        application.setJob_id("job456");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, application.getA_id());
        assertEquals("Pending", application.getStatus());
        assertEquals("company123", application.getC_id());
        assertEquals("job456", application.getJob_id());
    }

    @Test
    public void testSaveApplication() {
        when(entityManager.merge(application)).thenReturn(application);

        Application savedApplication = entityManager.merge(application);

        assertEquals(application, savedApplication);
        verify(entityManager, times(1)).merge(application);
    }
}
