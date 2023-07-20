package com.application.careerserviceapplication.controllers;

import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.models.Resume;
import com.application.careerserviceapplication.services.CandidateService;
import com.application.careerserviceapplication.services.LoginService;
import com.application.careerserviceapplication.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;

@Controller
public class CandidateController {

    @Autowired
    private CandidateService candidateService = new CandidateService();

    @Autowired
    private LoginService loginService = new LoginService();

    @Autowired
    private ResumeService resumeService = new ResumeService();

    @GetMapping("/addCandidate")
//    @RequestMapping(value = "/addCandidate",method = RequestMethod.POST)
    public String addCandidate(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam String email, @RequestParam String address1,
                             @RequestParam String address2, @RequestParam String password, @RequestParam Date birthday
            , @RequestParam String from, Model model) throws ClassNotFoundException, SQLException {
        System.out.println("addCandidate:: Going to add a candidate > " + firstName);
        Candidate candidate = new Candidate();

        candidate.setFirstName(firstName);
        candidate.setLname(lastName);
        candidate.setEmail(email);
//        candidate.setMobile(Long.parseLong(mobileNumber));
        String address = address1 + " " + address2;
        candidate.setAddress(address);
        candidate.setBirthday(birthday);

        System.out.println("Candidate info : " + candidate);

        String status = candidateService.getAllCandidate(email);

        System.out.println("old pat  >>> " + status);
        if (status.equalsIgnoreCase("notExist")) {

            candidateService.saveUser(candidate);

            loginService.savedLoginCredentials(email, password, "C");

            model.addAttribute("status", "success");
            model.addAttribute("message", "success");
        } else {
            if (from.equals("A")) {
                model.addAttribute("status", "failed");
                model.addAttribute("message", "Email already exists with another user!");
                return "admin";
            } else {
                model.addAttribute("status", "failed");
                model.addAttribute("message", "Email already exists with another user!");
                return "register";
            }
        }
        if (from.equals("A"))
            return "redirect:/admin";
        else
            return "redirect:/user_login";
    }
    @GetMapping("/candidate/{pid}")
    public String goPatient(@PathVariable String pid, Model model) {
        model.addAttribute("pid", pid);
        System.out.println("goCandidate:: The Candidate pid is : " + pid);
        return "candidate";
    }

   
}
