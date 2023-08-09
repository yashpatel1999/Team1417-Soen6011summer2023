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
}
