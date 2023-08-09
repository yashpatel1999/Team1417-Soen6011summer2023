package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.models.Resume;
import com.application.careerserviceapplication.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public void saveResume(Resume resume) {
        try {
            System.out.println("saveResume:: Resume details is >> " + resume);
            System.out.println(resumeRepository);
            resumeRepository.save(resume);
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }

    public void updateResume(Resume resume)
    {
        try
        {
            Statement st = DatabaseAccess.getConnection();
            String email = resume.getEmail();
            String query  = "delete from resumedetails where email_id = '" +email +"';";
            int rowsUpdated = st.executeUpdate(query);
            if (rowsUpdated ==1)
            {
                resumeRepository.save(resume);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }

    public String checkResume(String email) throws SQLException, ClassNotFoundException {
        System.out.println("check Resume:: Going to fetch details of ");
        String status = "notExist";
        try {
            Statement st = DatabaseAccess.getConnection();
            if (st.isClosed())
            {
                System.out.println("connection unsuccesful");
            }
            String query = "SELECT * FROM resumedetails where email_id = \"" + email + "\";";
            System.out.println(">> " + query);
            ResultSet resultSet = st.executeQuery(query);


            ResultSetMetaData md = resultSet.getMetaData();
            System.out.println("-------here");
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
