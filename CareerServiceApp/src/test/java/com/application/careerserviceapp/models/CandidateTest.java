package com.application.careerserviceapplication.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidateTest {

    private Candidate candidate=new Candidate();
    private Date testDate;

    @BeforeEach
    public void setUp() {
        candidate = new Candidate();
        candidate.setC_id(1L);
        candidate.setFirstName("John");
        candidate.setLname("Doe");
        candidate.setAddress("123 Main St");
        candidate.setEmail("john.doe@example.com");
        testDate = new Date(123456789000L); // Example timestamp: January 2, 1970, 10:17:36 AM GMT
        candidate.setBirthday(testDate);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, candidate.getC_id());
        assertEquals("John", candidate.getFirstName());
        assertEquals("Doe", candidate.getLastName());
        assertEquals("123 Main St", candidate.getAddress());
        assertEquals("john.doe@example.com", candidate.getEmail());
        assertEquals(testDate, candidate.getBirthday());

    }

    @Test
    public void testToString() {
        String expectedToString = "1 ~ John ~ Doe ~ 123 Main St ~ john.doe@example.com";
        assertEquals(expectedToString, candidate.toString());
    }
}
