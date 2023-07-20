package com.application.careerserviceapplication.services;


import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public void saveUser(Candidate candidate) {
        try {
            System.out.println("saveUser:: candidate is >> " + candidate);
            System.out.println(candidateRepository);
            candidateRepository.save(candidate);
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }

    public Candidate getCandidate(String c_id) {
        System.out.println("getCandidate:: Going to fetch details of " + c_id);
        try {
            Candidate candidate = candidateRepository.getReferenceById(c_id);
            System.out.println("Fetched patient is :" + candidate);
            return candidate;
        } catch (Exception e) {
            System.out.println("returning null");
            e.printStackTrace();
            return null;
        }
    }


    public String getAllCandidate(String email) throws SQLException, ClassNotFoundException {
        System.out.println("getAllCandidates:: Going to fetch details of ");
        String status = "notExist";
        try {
            Statement st = DatabaseAccess.getConnection();
            if (st.isClosed())
            {
                System.out.println("connection unsuccesful");
            }
            String query = "SELECT * FROM user_login where user_id = \"" + email + "\";";
            System.out.println(">> " + query);
            ResultSet resultSet = st.executeQuery(query);


            ResultSetMetaData md = resultSet.getMetaData();
            System.out.println(">> " + md.getColumnCount() + " -- " + resultSet.getRow());

            while (resultSet.next()) {
                status = "exist";
                break;
            }
//		else {
//			status = "notExist";
//		}
        } catch (Exception e) {
            status = "notExist";
        } finally {
            System.out.println("Going to close connection");
            DatabaseAccess.connection.close();
        }
        return status;
    }

}
