package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.repositories.CandidateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest
public class CandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateService candidateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Given
        Candidate mockCandidate = new Candidate();
        // Set up any required properties on the mockCandidate object

        // When
        candidateService.saveUser(mockCandidate);

        // Then
        verify(candidateRepository, times(1)).save(mockCandidate);
    }
    @Test
    public void testGetAllJobs() throws Exception {
        // Given
        String mockId = "testId";

        // Mocking DatabaseAccess and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Set up ResultSet behavior
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate multiple rows
        when(mockResultSet.getString("job_id")).thenReturn("job1", "job2"); // Simulate job ids

        // Mock the DatabaseAccess class
//        when(DatabaseAccess.getConnection()).thenReturn(mockConnection);
//        doNothing().when(DatabaseAccess.connection).close(); // Mock the connection close

        // When
        String result = candidateService.getAllJobs(mockId);

        // Then
        String expectedJson =candidateService.getAllJobs(mockId);
        assertEquals(expectedJson, result);
    }
    @Test
    public void testGetSingleJob() throws Exception {
        // Given
        String mockJobId = "testJobId";

        // Mocking DatabaseAccess and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Set up ResultSet behavior
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one row
        when(mockResultSet.getString("job_id")).thenReturn("job1"); // Simulate job id
        when(mockResultSet.getString("company_location")).thenReturn("Location1"); // Simulate company location
        when(mockResultSet.getString("company_name")).thenReturn("Company1"); // Simulate company name
        // Add more simulating statements as needed

        // Mock the DatabaseAccess class
        when(DatabaseAccess.getConnection()).thenReturn((Statement) mockConnection);
        doNothing().when(DatabaseAccess.connection).close(); // Mock the connection close

        // When
        String result = candidateService.getSingleJob(mockJobId);

        // Then
        String expectedJson = "{\"Job_details\":"
                + "{\"job_id\":\"job1\",\"company_location\":\"Location1\",\"company_name\":\"Company1\"}"
                + "}";
        assertEquals(expectedJson, result);
    }
    @Test
    public void testGetAllCandidate() throws Exception {
        // Given
        String mockEmail = "test@example.com";

        // Mocking DatabaseAccess and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        ResultSetMetaData mockMetaData = mock(ResultSetMetaData.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Set up ResultSet behavior
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one row
        when(mockResultSet.getMetaData()).thenReturn(mockMetaData);
        when(mockMetaData.getColumnCount()).thenReturn(1); // Simulate one column

        // Mock the DatabaseAccess class
//        doNothing().when(mockConnection).close(); // Mock the connection close
//        doReturn(mockConnection).when(DatabaseAccess.getConnection());
        // When
        String result = candidateService.getAllCandidate(mockEmail);

        // Then
        assertEquals("notExist", result);
    }


//    @Test
//    public void testGetCandidate() {
//        // Given
//        String mockCId = "testCandidateId";
//        Candidate mockCandidate = new Candidate();
//        // Set up any required properties on the mockCandidate object
//        when(candidateRepository.getReferenceById(mockCId)).thenReturn(mockCandidate);
//
//        // When
//        Candidate result = candidateService.getCandidate(mockCId);
//
//        // Then
//        verify(candidateRepository, times(1)).getReferenceById(mockCId);
//        // Add assertions to check the result
//    }
//
//    @Test
//    public void testGetAllJobs() throws SQLException, ClassNotFoundException {
//        // Given
//        String mockId = "testId";
//        // Set up any required properties or data for the test
//        // Mock DatabaseAccess.getConnection() and ResultSet for DatabaseAccess
//        // Verify statements and assertions
//
//        // When
//        String result = candidateService.getAllJobs(mockId);
//
//        // Then
//        // Add assertions to check the result
//    }
//
//    @Test
//    public void testGetSingleJob() {
//        // Given
//        String mockJobId = "testJobId";
//        // Set up any required properties or data for the test
//        // Mock DatabaseAccess.getConnection() and ResultSet for DatabaseAccess
//        // Verify statements and assertions
//
//        // When
//        String result = candidateService.getSingleJob(mockJobId);
//
//        // Then
//        // Add assertions to check the result
//    }
//
//    @Test
//    public void testCheckCandidateApplied() throws SQLException, ClassNotFoundException {
//        // Given
//        String mockCId = "testCandidateId";
//        String mockJId = "testJobId";
//        // Set up any required properties or data for the test
//        // Mock DatabaseAccess.getConnection() and ResultSet for DatabaseAccess
//        // Verify statements and assertions
//
//        // When
//        String result = candidateService.checkCandidateApplied(mockCId, mockJId);
//
//        // Then
//        // Add assertions to check the result
//    }
//
//    @Test
//    public void testGetCandidateData() throws SQLException, ClassNotFoundException {
//        // Given
//        String mockCId = "testCandidateId";
//        // Set up any required properties or data for the test
//        // Mock DatabaseAccess.getConnection() and ResultSet for DatabaseAccess
//        // Verify statements and assertions
//
//        // When
//        String result = candidateService.getCandidateData(mockCId);
//
//        // Then
//        // Add assertions to check the result
//    }
//
//    @Test
//    public void testGetAppliedJob() throws SQLException, ClassNotFoundException {
//        // Given
//        String mockCId = "testCandidateId";
//        // Set up any required properties or data for the test
//        // Mock DatabaseAccess.getConnection() and ResultSet for DatabaseAccess
//        // Verify statements and assertions
//
//        // When
//        String result = candidateService.getAppliedJob(mockCId);
//
//        // Then
//        // Add assertions to check the result
//    }
//
//    @Test
//    public void testGetSelectedJob() throws SQLException, ClassNotFoundException {
//        // Given
//        String mockCId = "testCandidateId";
//        // Set up any required properties or data for the test
//        // Mock DatabaseAccess.getConnection() and ResultSet for DatabaseAccess
//        // Verify statements and assertions
//
//        // When
//        String result = candidateService.getSelectedJob(mockCId);
//
//        // Then
//        // Add assertions to check the result
//    }
//
//    @Test
//    public void testPostResumeDetailsProfile() throws SQLException, ClassNotFoundException {
//        // Given
//        String mockCId = "testCandidateId";
//        // Set up any required properties or data for the test
//        // Mock DatabaseAccess.getConnection() and ResultSet for DatabaseAccess
//        // Verify statements and assertions
//
//        // When
//        String result = candidateService.postResumeDetailsProfile(mockCId);
//
//        // Then
//        // Add assertions to check the result
//    }
}
