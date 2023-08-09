package com.application.careerserviceapplication.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EmployerTest {

    private Employer employer;

    @Before
    public void setUp() {
        employer = new Employer();
        employer.setFirstName("John");
        employer.setLastName("Doe");
        employer.setEmail("john@example.com");
        employer.setCompany_name("ABC Corp");
        employer.setMobile(1234567890L);
    }

    @Test
    public void testGetters() {
        assertEquals("John", employer.getFirstName());
        assertEquals("Doe", employer.getLastName());
        assertEquals("john@example.com", employer.getEmail());
        assertEquals("ABC Corp", employer.getCompany_name());
        assertEquals(1234567890L, employer.getMobile());
    }

    @Test
    public void testToString() {
        String expectedToString = "John ~ Doe ~ ABC Corp ~ 1234567890 ~ john@example.com";
        assertEquals(expectedToString, employer.toString());
    }

    @Test
    public void testSetters() {
        employer.setFirstName("Jane");
        employer.setLastName("Smith");
        employer.setEmail("jane@example.com");
        employer.setCompany_name("XYZ Inc");
        employer.setMobile(9876543210L);

        assertEquals("Jane", employer.getFirstName());
        assertEquals("Smith", employer.getLastName());
        assertEquals("jane@example.com", employer.getEmail());
        assertEquals("XYZ Inc", employer.getCompany_name());
        assertEquals(9876543210L, employer.getMobile());
    }
}

