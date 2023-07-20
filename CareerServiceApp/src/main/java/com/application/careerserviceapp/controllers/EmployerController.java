package com.application.careerserviceapplication.controllers;

import com.application.careerserviceapplication.models.Employer;
import com.application.careerserviceapplication.models.Job;
import com.application.careerserviceapplication.services.EmployerService;
import com.application.careerserviceapplication.services.JobService;
import com.application.careerserviceapplication.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EmployerController {
    @Autowired
    private LoginService loginService = new LoginService();

    @Autowired
    private EmployerService employerService = new EmployerService();

    @Autowired
    private JobService jobService = new JobService();

//    Model model1 = null;

    @GetMapping("/addEmployer")
    public String addEmployer(@RequestParam String firstName, @RequestParam String lastName,
                              @RequestParam String email, @RequestParam String companyName,
                              @RequestParam String mobile, @RequestParam String password) {
        System.out.println("Going to add a Doctor > " + firstName + "  " + mobile);
        Employer employer = new Employer();

        employer.setFirstName(firstName);
        employer.setLastName(lastName);
        employer.setEmail(email);
        employer.setMobile(Long.parseLong(mobile));
        employer.setCompany_name(companyName);
        System.out.println("Employer info: "+ employer);

        employerService.saveUser(employer);
        loginService.savedLoginCredentials(email,password,"E");

        return "redirect:/user_login";
    }
    @GetMapping("/employer/{pid}")
    public String goEmployer(@PathVariable String pid, Model model) {
        model.addAttribute("pid", pid);
//        model1.addAttribute("pid",pid);
        System.out.println("goEmployer:: The employer pid is : " + pid);
        return "employer";
    }
}
