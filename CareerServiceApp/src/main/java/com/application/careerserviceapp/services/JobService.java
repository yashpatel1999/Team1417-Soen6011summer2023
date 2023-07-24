package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.Job;
import com.application.careerserviceapplication.models.Resume;
import com.application.careerserviceapplication.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public void saveJobs(Job job) {
        try {
            System.out.println("savejob:: job details is >> " + job);
            System.out.println(jobRepository);
            jobRepository.save(job);
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }

    public String checkJobExist(String jobId) throws SQLException, ClassNotFoundException {
        System.out.println("check Job Exist:: Going to fetch details of ");
        String status = "notExist";
        try {
            Statement st = DatabaseAccess.getConnection();
            if (st.isClosed())
            {
                System.out.println("connection unsuccesful");
            }
            String query = "SELECT * FROM Job_Details where job_id = \"" + jobId + "\";";
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
