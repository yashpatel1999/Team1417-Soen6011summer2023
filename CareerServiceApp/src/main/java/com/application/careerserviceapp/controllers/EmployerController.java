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

    @GetMapping("/createJobPosting")
    public String createResume(@RequestParam String id, Model model)
    {
        model.addAttribute("pid",id);
        System.out.println("Create Job: by Employee"+ id);
        return "createJobPosting";
    }
    @GetMapping("/addJobPosting")
    public String addJobposting(
            @RequestParam(value = "jobId") String jobId,
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "desc",required = false) String desc,
//            @RequestParam(value = "jobposted") String jobposted,
            @RequestParam(value = "companyName",required = false)String companyName,
            @RequestParam(value = "companyLocation",required = false) String companyLocation,Model model
    ) throws SQLException, ClassNotFoundException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Job job = new Job();
        job.setJid(jobId);
        job.setTitle(title);
        job.setDesc(desc);
        job.setJobPosted((new Date()));
        job.setCompanyName(companyName);
        job.setCompanyLocation(companyLocation);

        String email = (String) model.getAttribute("pid");
        job.setPostedBy(email);

//        employerService.getEmployer()
        System.out.println("Job info : " + job);

        String status = jobService.checkJobExist(jobId);

        System.out.println("old pat  >>> " + status);

        if (status.equalsIgnoreCase("notExist")) {
            jobService.saveJobs(job);
            model.addAttribute("status", "success");
            model.addAttribute("message", "success");
            return "redirect:/success/" + email;
        } else {
            return "redirect:/failed/" + email;
        }
    }
    @GetMapping("/success_job/{pid}")
    public String checkSuccessJob(@PathVariable String pid, Model model) {
        model.addAttribute("pid", pid);
        System.out.println("JOB uploaded successfully : " + pid);
        return "success";
    }
    @GetMapping("/failed_job/{pid}")
    public String checkFailedJob(@PathVariable String pid, Model model) {
        model.addAttribute("pid", pid);
        System.out.println("JOB didn't uploaded successfully: " + pid);
        return "failed";
    }
}
