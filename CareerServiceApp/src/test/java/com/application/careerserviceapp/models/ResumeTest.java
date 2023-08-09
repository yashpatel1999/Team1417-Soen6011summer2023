package com.application.careerserviceapplication.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ResumeTest {

    private Resume resume;

    @Before
    public void setUp() {
        resume = new Resume();
        resume.setEmail("john@example.com");
        resume.setFirstName("John");
        resume.setLastName("Doe");
        resume.setBirthday(new Date());
        resume.setCno(1234567890L);
        resume.setEducation("Bachelor's in Computer Science");
        resume.setWorkex("3 years as a software engineer");
        resume.setProjects("Developed project X and Y");
        resume.setSkills("Java, Python, SQL");
    }

    @Test
    public void testGetters() {
        assertEquals("john@example.com", resume.getEmail());
        assertEquals("John", resume.getFirstName());
        assertEquals("Doe", resume.getLastName());
        // Test other getters...
    }

    @Test
    public void testSetters() {
        resume.setEmail("jane@example.com");
        resume.setFirstName("Jane");
        resume.setLastName("Smith");
        // Set other properties...

        assertEquals("jane@example.com", resume.getEmail());
        assertEquals("Jane", resume.getFirstName());
        assertEquals("Smith", resume.getLastName());
        // Test other setters...
    }

    @Test
    public void testEducation() {
        assertEquals("Bachelor's in Computer Science", resume.getEducation());
    }

    @Test
    public void testWorkex() {
        assertEquals("3 years as a software engineer", resume.getWorkex());
    }

    @Test
    public void testProjects() {
        assertEquals("Developed project X and Y", resume.getProjects());
    }

    @Test
    public void testSkills() {
        assertEquals("Java, Python, SQL", resume.getSkills());
    }
}

