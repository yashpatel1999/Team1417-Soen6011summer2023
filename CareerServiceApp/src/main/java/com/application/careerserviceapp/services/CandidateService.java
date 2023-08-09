package com.application.careerserviceapplication.services;


import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.repositories.CandidateRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            if (st.isClosed()) {
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

    public String getAllJobs(String id) throws SQLException, ClassNotFoundException {
        System.out.println("getting All jobs for id::" + id);
        JSONObject jList = new JSONObject();
        try {
            Statement st = DatabaseAccess.getConnection();
            String query = "SELECT * from job_details order by posted_by ASC;";
            ResultSet resultSet = st.executeQuery(query);

            JSONArray jb = new JSONArray();
            ResultSetMetaData md = resultSet.getMetaData();
            System.out.println("c_count::" + md.getColumnCount());
            if (md.getColumnCount() > 0) {
                while (resultSet.next()) {
                    String jid = resultSet.getString("job_id");
                    String company_location = resultSet.getString("company_location");
                    String company_name = resultSet.getString("company_name");
                    String job_desc = resultSet.getString("job_desc");
                    String job_title = resultSet.getString("job_title");
                    Date job_posted = resultSet.getDate("job_posted");
                    JSONObject obj = new JSONObject();
                    System.out.println(jid + " - " + company_location + " - " + company_name + " - " + job_desc + " - " + job_title + " - " + job_posted);
                    obj.put("job_id", jid);
                    obj.put("company_location", company_location);
                    obj.put("company_name", company_name);
                    obj.put("job_desc", job_desc);
                    obj.put("job_title", job_title);
                    obj.put("job_posted", job_posted);
                    jb.add(obj);
                }
            }
            jList.put("Job_details", jb);
            query = "SELECT employer.fname,employer.lname from employer inner join job_details on employer.email_id = job_details.posted_by;";
            resultSet = st.executeQuery(query);
            JSONArray emp = new JSONArray();
            md = resultSet.getMetaData();
            if (md.getColumnCount() > 0) {
                while (resultSet.next()) {
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    JSONObject empObj = new JSONObject();
                    empObj.put("fname", fname);
                    empObj.put("lname", lname);
                    emp.add(empObj);
                }
            }
            jList.put("emp_details", emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jList.toJSONString();
    }

    public String getSingleJob(String j_id) {
        System.out.println("getting job details for id::" + j_id);
        JSONObject jList = new JSONObject();
        try {
            Statement st = DatabaseAccess.getConnection();
            String query = "SELECT * from job_details where " + "job_id = \'" + j_id + "\';";
            ResultSet resultSet = st.executeQuery(query);

            JSONArray jb = new JSONArray();
            ResultSetMetaData md = resultSet.getMetaData();
            System.out.println("c_count::" + md.getColumnCount());
            if (md.getColumnCount() > 0) {
                while (resultSet.next()) {
                    String jid = resultSet.getString("job_id");
                    String company_location = resultSet.getString("company_location");
                    String company_name = resultSet.getString("company_name");
                    String job_desc = resultSet.getString("job_desc");
                    String job_title = resultSet.getString("job_title");
                    Date job_posted = resultSet.getDate("job_posted");
                    JSONObject obj = new JSONObject();
                    System.out.println(jid + " - " + company_location + " - " + company_name + " - " + job_desc + " - " + job_title + " - " + job_posted);
                    obj.put("job_id", jid);
                    obj.put("company_location", company_location);
                    obj.put("company_name", company_name);
                    obj.put("job_desc", job_desc);
                    obj.put("job_title", job_title);
                    obj.put("job_posted", job_posted);
                    jb.add(obj);
                }
            }
            jList.put("Job_details", jb);
            query = "select fname,lname from employer where email_id=(SELECT posted_by from job_details where job_id ='" + j_id + "');";
            resultSet = st.executeQuery(query);
            JSONArray emp = new JSONArray();
            md = resultSet.getMetaData();
            if (md.getColumnCount() > 0) {
                while (resultSet.next()) {
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    JSONObject empObj = new JSONObject();
                    empObj.put("fname", fname);
                    empObj.put("lname", lname);
                    emp.add(empObj);
                }
            }
            jList.put("emp_details", emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jList.toJSONString();
    }

    public String checkCandidateApplied(String cid, String jid) throws SQLException, ClassNotFoundException {
        System.out.println("check Candidate Applied :: Going to fetch details of ");
        String status = "notExist";
        try {
            Statement st = DatabaseAccess.getConnection();
            if (st.isClosed()) {
                System.out.println("connection unsuccesful");
            }
            String query = "Select c_id from application_details where job_id = '" + jid + "';";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData md = resultSet.getMetaData();
            List<String> listCandidate = new ArrayList<>();
            if (md.getColumnCount() > 0) {
                while (resultSet.next()) {
                    String c_id = resultSet.getString("c_id");
                    listCandidate.add(c_id);
                }
            }
            boolean ans = listCandidate.contains(cid);
            if (ans) {
                status = "exist";
            }
        }catch (Exception e)
        {
            status = "notExist";
        } finally {
        System.out.println("Going to close connection");
        DatabaseAccess.connection.close();
        }
        return status;
    }
    public String getCandidateData(String cid) throws SQLException, ClassNotFoundException {
        System.out.println("Get candidate data");
        JSONObject crList =new JSONObject();
        try
        {
            Statement st=DatabaseAccess.getConnection();
            String query1 = "SELECT email_id,fname,lname,date_of_birth,address from candidate where email_id='"+ cid+"'";
            ResultSet resultSet1 = st.executeQuery(query1);
            ResultSetMetaData md1= resultSet1.getMetaData();
            JSONArray cb = new JSONArray();
            if(md1.getColumnCount()>0)
            {
                while (resultSet1.next())
                {
                    String email = resultSet1.getString("email_id");
                    String fname = resultSet1.getString("fname");
                    String lname = resultSet1.getString("lname");
                    String address = resultSet1.getString("address");
                    Date dob = resultSet1.getDate("date_of_birth");
                    JSONObject canObj = new JSONObject();
                    canObj.put("email_id",email);
                    canObj.put("fname",fname);
                    canObj.put("lname",lname);
                    canObj.put("address10",address);
                    canObj.put("date_of_birth",dob);
                    cb.add(canObj);
                }
            }
            crList.put("can_details",cb);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return crList.toJSONString();
    }
}
