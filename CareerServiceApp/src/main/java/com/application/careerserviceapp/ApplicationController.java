package com.application.careerserviceapplication;

import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.services.CandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {


    CandidateService candidateService=new CandidateService();
    @GetMapping("/")
    public String goIndex()
    {
        System.out.println("Hello");
//        candidateService.saveUser(new Candidate("1"));
        return "index";
    }
    @GetMapping("/register")

    public String goRegister() {
        System.out.println("register");
        return "register";
    }

    @GetMapping("/user_login")
    public String goLogin() {
        System.out.println("login");
        return "user_login";
    }
}
