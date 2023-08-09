package com.application.careerserviceapplication.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class JobTest {

    private Job job;
    private Employer employer;

    @Before
    public void setUp() {
        job = new Job();
        job.setId("123");
        job.setTitle("Software Engineer");
        job.setDesc("Developing software applications");
        job.setJobPosted(new Date());
        job.setCompanyName("ABC Corp");
        job.setCompanyLocation("New York");

        employer = new Employer();
        employer.setFirstName("John");
        employer.setLastName("Doe");
        employer.setEmail("john@example.com");
        employer.setCompany_name("ABC Corp");
        employer.setMobile(1234567890L);
        job.setPostedBy(employer);
    }

    @Test
    public void testGetters() {
        assertEquals("123", job.getId());
        assertEquals("Software Engineer", job.getTitle());
        assertEquals("Developing software applications", job.getDesc());
        // Test other getters...
    }

    @Test
    public void testSetters() {
        job.setId("456");
        job.setTitle("Web Developer");
        job.setDesc("Creating web applications");
        // Set other properties...

        assertEquals("456", job.getId());
        assertEquals("Web Developer", job.getTitle());
        assertEquals("Creating web applications", job.getDesc());
        // Test other setters...
    }

    @Test
    public void testPostedBy() {
        assertEquals(employer, job.getPostedBy());
    }
}
