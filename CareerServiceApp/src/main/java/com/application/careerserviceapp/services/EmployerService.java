package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.Employer;
import com.application.careerserviceapplication.models.Job;
import com.application.careerserviceapplication.repositories.EmployerRepository;
import com.application.careerserviceapplication.repositories.JobRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private  JobRepository jobRepository;

    public void saveUser(Employer employer)
    {
        System.out.println("Employer is >> "+employer);
        employerRepository.save(employer);
    }

    public Employer getEmployer(String eid) {
        System.out.println("getEmployer:: Going to fetch details of " + eid);
        Employer employer = employerRepository.getEmployerByemail(eid);
        System.out.println("Fetched Counsellor is :" + employer);
        return employer;
    }

//    public List<Job> findAll()
//    {
//        return jobRepository.findAll();
//    }
//    public String getJobs(String id) throws JsonProcessingException {
//        JSONObject response = new JSONObject();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(id);
//        String jid=rootNode.get("jid").asText();
//        System.out.println("getAppointments::" + jid);
//        try{
//            List<Job> listOfJobs= jobRepository.findAll();
//            System.out.println(listOfJobs.toString());
//            JSONArray jobs= new JSONArray();
//
//            for(int i=0;i<listOfJobs.size();i++)
//            {
//                JSONObject jb=new JSONObject();
//                jb.put("job_id",listOfJobs.get(i).getId());
//                jb.put("company_location",listOfJobs.get(i).getCompanyLocation());
//                jb.put("company_name",listOfJobs.get(i).getCompanyName());
//                jb.put("Job_desc",listOfJobs.get(i).getDesc());
//                jb.put("job_posted",listOfJobs.get(i).getJobPosted());
//                jb.put("job_title",listOfJobs.get(i).getTitle());
//                jobs.add(jb);
//            }
//            response.put("jobs",jobs);
//            response.put("status","success");
//        }catch (Exception e)
//        {
//            response.put("jobs","");
//            response.put("status","failed");
//        }
//        System.out.println(response.toJSONString());
//        return response.toJSONString();
//    }
    public String getJobs(String id) throws SQLException, ClassNotFoundException
    {
        System.out.println("getting jobs posted by id::"+ id);
        JSONObject jList=new JSONObject();
        try {
            Statement st=DatabaseAccess.getConnection();
            String query = "SELECT * from job_details where "+"posted_by = \'" + id + "\';";
            ResultSet resultSet= st.executeQuery(query);

            JSONArray jb=new JSONArray();
            ResultSetMetaData md= resultSet.getMetaData();
            System.out.println("c_count::"+md.getColumnCount());
            if(md.getColumnCount()>0)
            {
                while (resultSet.next())
                {
                    String jid=resultSet.getString("job_id");
                    String company_location=resultSet.getString("company_location");
                    String company_name=resultSet.getString("company_name");
                    String job_desc=resultSet.getString("job_desc");
                    String job_title=resultSet.getString("job_title");
                    Date job_posted=resultSet.getDate("job_posted");
                    JSONObject obj=new JSONObject();
                    System.out.println(jid + " - " + company_location + " - " + company_name + " - " + job_desc+" - "+job_title+" - "+job_posted);
                    obj.put("job_id",jid);
                    obj.put("company_location",company_location);
                    obj.put("company_name",company_name);
                    obj.put("job_desc",job_desc);
                    obj.put("job_title",job_title);
                    obj.put("job_posted",job_posted);
                    jb.add(obj);
                }
            }
            jList.put("Job_details",jb);
            query= "SELECT fname,lname from employer where "+ "email_id = \'" + id + "\';";
            resultSet= st.executeQuery(query);
            JSONArray emp=new JSONArray();
            md= resultSet.getMetaData();
            if(md.getColumnCount()>0)
            {
                while (resultSet.next())
                {
                    String fname=resultSet.getString("fname");
                    String lname=resultSet.getString("lname");
                    JSONObject empObj = new JSONObject();
                    empObj.put("fname",fname);
                    empObj.put("lname",lname);
                    emp.add(empObj);
                }
            }
            jList.put("emp_details",emp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return jList.toJSONString();
    }
    public String postCandidate(String jid) throws SQLException, ClassNotFoundException {
        System.out.println("Getting List of Candidates applied ::");
        JSONObject cList=new JSONObject();
        try
        {
            Statement st=DatabaseAccess.getConnection();
            String query = "Select c_id from application_details where job_id = '" + jid + "' and status ='applied';";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData md= resultSet.getMetaData();
            JSONArray cb=new JSONArray();
            List<String> canList = new ArrayList<>();
            if (md.getColumnCount() > 0) {
                while (resultSet.next()) {
                    String c_id = resultSet.getString("c_id");
                    canList.add(c_id);
                }
            }
            for(String x : canList)
            {
                String query1 = "SELECT email_id,fname,lname,date_of_birth,address from candidate where email_id='"+ x+"'";
                ResultSet resultSet1 = st.executeQuery(query1);
                ResultSetMetaData md1= resultSet1.getMetaData();
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
            }
            cList.put("can_details",cb);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cList.toJSONString();
    }
    public String postResumeDetails(String cid) throws SQLException, ClassNotFoundException {
        System.out.println("posting resume details");
        JSONObject rList = new JSONObject();
        try{
            Statement st=DatabaseAccess.getConnection();
            String query = "select * from resumedetails where email_id= '"+cid+"'";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData md = resultSet.getMetaData();
            JSONArray res = new JSONArray();
            if(md.getColumnCount()>0)
            {
                while (resultSet.next())
                {
                    String email = resultSet.getString("email_id");
                    String fname =resultSet.getString("fname");
                    String lname =resultSet.getString("lname");
                    long c_no = resultSet.getLong("c_no");
                    String date = String.valueOf(resultSet.getDate("date_of_birth"));
                    String education  = resultSet.getString("education");
                    String skills  = resultSet.getString("skills");
                    String projects  = resultSet.getString("projects");
                    String workex  = resultSet.getString("workex");
                    JSONObject resObj =new JSONObject();
                    resObj.put("email_id",email);
                    resObj.put("fname",fname);
                    resObj.put("lname",lname);
                    resObj.put("c_no",c_no);
                    resObj.put("date",date);
                    resObj.put("education1",education);
                    resObj.put("skills1",skills);
                    resObj.put("projects1",projects);
                    resObj.put("workex1",workex);
                    res.add(resObj);
                }
            }
            rList.put("resumeDetails",res);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rList.toJSONString();
    }

    public String updateStatus(String cid, String jid, String status) throws SQLException, ClassNotFoundException {
        System.out.println("in update status");
        String sts = "failed";
        try
        {
            Statement st = DatabaseAccess.getConnection();
            String query = "UPDATE application_details SET status = '"+status+"' where job_id = '"+ jid+"' and c_id = '"+cid+"';";
            System.out.println(query);
            int rows = st.executeUpdate(query);
            sts = "success";
        }
        catch (Exception e)
        {
            sts="failed";
        }
        finally {
            System.out.println("going to close connection");
            DatabaseAccess.connection.close();
        }
        return sts;
    }
}
