package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.UserLogin;
import com.application.careerserviceapplication.repositories.LoginRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public void savedLoginCredentials(String userid, String pwd, String type)
    {
        UserLogin userLogin = new UserLogin();
        Base64.Encoder simpleEncoder = Base64.getEncoder();
        String encodedPassword = simpleEncoder.encodeToString(pwd.getBytes());
        System.out.println("Encoded password : " + pwd + "  -  " + encodedPassword);
        userLogin.setUid(userid);
        userLogin.setPassword(encodedPassword);
        userLogin.setType(type);
        if (type.equals("C")||type.equals("E"))
            userLogin.setApproved("Y");
        else
            userLogin.setApproved("N");
        System.out.println("Login info is >> " + userLogin);
        loginRepository.save(userLogin);
    }

    public UserLogin getLoginbyUser(UserLogin login) {
        System.out.println("Login info is >> " + login);
        return loginRepository.getUserLoginByuid(login.getUid());
    }

    public String updateUser(String req) throws SQLException {
        JSONObject response = new JSONObject();
        try {
            System.out.println("updateDoctor:: Going to update the doctor status" + req);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(req);
            String email = rootNode.get("id").asText();
            String action = rootNode.get("action").asText();
            String ac = "";
            String query = "";
            int rowsUpdated = 0;
            Statement st = DatabaseAccess.getConnection();
            if (action.equals("accept")) {
                ac = "Y";
                query = "UPDATE user_login SET approved = \'" + ac + "\' WHERE "
                        + "user_id = \"" + email + "\";";
                rowsUpdated = st.executeUpdate(query);
            } else {
                System.out.println("going to delete the user");
                ac = "N";
                query = "DELETE FROM user_login  WHERE "
                        + "user_id = \"" + email + "\";";
                System.out.println(query);
                boolean ans = st.execute(query);
            }

            System.out.println(query);

            System.out.println("Updated " + rowsUpdated + " row");
            response.put("status", "success");
        } catch (Exception e) {
            System.out.println("Failed to extract value.-- " + e.getMessage());
            response.put("status", "failed");
        } finally {
            System.out.println("Going to close connection");
            DatabaseAccess.connection.close();
        }
        return response.toJSONString();
    }

    public String removeUser(String req) throws SQLException {

        JSONObject response = new JSONObject();
        try {
            System.out.println("removeUser:: Going to remove the user status" + req);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(req);
            String email = rootNode.get("id").asText();


            Statement st = DatabaseAccess.getConnection();
            String query = "DELETE FROM user_login WHERE "
                    + "user_id = \"" + email + "\";";
            System.out.println(query);
            int rowsUpdated = st.executeUpdate(query);
            System.out.println("D " + rowsUpdated + " row");

            query = "DELETE FROM patient WHERE "
                    + "email = \"" + email + "\";";
            System.out.println(query);
            rowsUpdated = st.executeUpdate(query);
            System.out.println("D " + rowsUpdated + " row");


            response.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to extract value.");
            response.put("status", "failed");
        } finally {
            System.out.println("Going to close connection");
            DatabaseAccess.connection.close();
        }
        return response.toJSONString();
    }
}
