package com.application.careerserviceapplication.controllers;

import com.application.careerserviceapplication.models.Employer;
import com.application.careerserviceapplication.models.Job;
import com.application.careerserviceapplication.services.EmployerService;
import com.application.careerserviceapplication.services.JobService;
import com.application.careerserviceapplication.services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;
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
    public String goEmployer(@PathVariable String pid, Model model) throws JsonProcessingException, SQLException, ClassNotFoundException {
        model.addAttribute("pid", pid);
//        model1.addAttribute("pid",pid);
        System.out.println("goEmployer:: The employer pid is : " + pid);
        String response=employerService.getJobs(pid);
        System.out.println(response);
        return "employer";
    }

    @GetMapping("/createJobPosting")
    public String createJob(@RequestParam String id, Model model)
    {
        model.addAttribute("pid",id);
        System.out.println("Create Job: by Employee"+ id);
        return "createJobPosting";
    }

    @GetMapping("/viewJobPosting")
    public String viewJobPosting(@RequestParam String id, Model model)
    {
        model.addAttribute("id",id);
        System.out.println("view Job Posted by::"+ id);
        return "viewJobPosting";
    }

    @GetMapping("/addJobPosting")
    public String addJobposting(
            @RequestParam(value = "jobId") String jobId,
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "desc",required = false) String desc,
//            @RequestParam(value = "jobposted") String jobposted,
            @RequestParam(value = "companyName",required = false)String companyName,
            @RequestParam(value = "companyLocation",required = false) String companyLocation,
            @RequestParam String href,Model model
    ) throws SQLException, ClassNotFoundException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Job job = new Job();
        Employer employer=new Employer();
        employer.setEmail(href);
        job.setJid(jobId);
        job.setTitle(title);
        job.setDesc(desc);
        job.setJobPosted((new Date()));
        job.setCompanyName(companyName);
        job.setCompanyLocation(companyLocation);
        job.setPostedBy(employer);
////        URL url = new URL(request.getRequestURL().toString());
//        String email = (String) model.getAttribute("pid");
////        System.out.println(email.toString());
////        job.setPostedBy(email);
////        employerService.getEmployer()
//        System.out.println("id:"+href);
        System.out.println("Job info : " + job);

        String status = jobService.checkJobExist(jobId);

        System.out.println("old pat  >>> " + status);

        if (status.equalsIgnoreCase("notExist")) {
            jobService.saveJobs(job);
            model.addAttribute("status", "success");
            model.addAttribute("message", "success");
            return "jobList";
        } else {
            return "failed";
        }
    }
  
    @PostMapping("/getJobs")
    public ResponseEntity<String> getJobs(@RequestParam String id) throws JsonProcessingException, SQLException, ClassNotFoundException {
        System.out.println("get jobs");
        String response=employerService.getJobs(id);
//        System.out.println("");
        return ResponseEntity.ok(response);
    }

    
    @PostMapping("/postResumeDetails")
    public  ResponseEntity<String> postResumeDetails(@RequestParam String cid) throws SQLException, ClassNotFoundException {
        System.out.println("Post Resume details");
        String response = employerService.postResumeDetails(cid);
        return ResponseEntity.ok(response);
    }
   
}
