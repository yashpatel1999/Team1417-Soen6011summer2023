package com.application.careerserviceapplication.controllers;

import com.application.careerserviceapplication.models.Application;
import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.models.Job;
import com.application.careerserviceapplication.models.Resume;
import com.application.careerserviceapplication.services.ApplicationService;
import com.application.careerserviceapplication.services.CandidateService;
import com.application.careerserviceapplication.services.LoginService;
import com.application.careerserviceapplication.services.ResumeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ApplicationService applicationService = new ApplicationService();

    @GetMapping("/addCandidate")
//    @RequestMapping(value = "/addCandidate",method = RequestMethod.POST)
    public String addCandidate(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam String email, @RequestParam String address1,
                             @RequestParam String address2, @RequestParam String password, @RequestParam Date birthday
            , @RequestParam String from, Model model) throws ClassNotFoundException, SQLException {
        System.out.println("addCandidate:: Going to add a candidate > " + firstName + " "+ lastName);
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

    @GetMapping("/createResume")
    public String createResume(@RequestParam String id, Model model)
    {
        model.addAttribute("pid",id);
        System.out.println("Create resume: by candidate"+ id);
        return "create_resume";
    }

    @PostMapping("/postCandidateResumeData")
    public  ResponseEntity<String> postCandidateResumedata(@RequestParam String cid) throws SQLException, ClassNotFoundException {
        System.out.println("Post Resume details");
        String response = candidateService.getCandidateData(cid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/viewAllJobs")
    public String viewAllJobs(@RequestParam String id, Model model)
    {
        model.addAttribute("pid",id);
        System.out.println("View All Jobs Listed::"+ id);
        return "viewAllJobPostings";
    }


    @GetMapping("/processResume")
    public String processResume(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "dob", required = false)Date dob,
            @RequestParam(value = "contactNumber", required = false) String contactNumber,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "education", required = false) String education,
            @RequestParam(value = "workExperience", required = false) String workExperience,
            @RequestParam(value = "projects", required = false) String projects,
            @RequestParam(value = "skills", required = false) String skills, Model model) throws SQLException, ClassNotFoundException {
        Resume resume = new Resume();
        resume.setFirstName(firstName);
        resume.setLastName(lastName);
        resume.setEmail(email);
        resume.setBirthday(dob);
        resume.setCno(Long.parseLong(contactNumber));
        resume.setEducation(education);
        resume.setWorkex(workExperience);
        resume.setProjects(projects);
        resume.setSkills(skills);

        System.out.println("Resume info : " + resume);

        String status = resumeService.checkResume(email);

        System.out.println("old pat  >>> " + status);

        if (status.equalsIgnoreCase("notExist")) {
            resumeService.saveResume(resume);
            model.addAttribute("status", "success");
            model.addAttribute("message", "Resume uploaded successfully");
            return "redirect:/viewAllJobs?id="+email;
        } else {
            model.addAttribute("status", "failed");
            model.addAttribute("message", "You Already uploaded your Resume");
            return "create_resume";
        }
    }
    @GetMapping("/success/{pid}")
    public String goSuccess(@PathVariable String pid, Model model) {
        model.addAttribute("pid", pid);
        System.out.println("resume uploaded successfully : " + pid);
        return "success";
    }
    @GetMapping("/failed/{pid}")
    public String checkFailed(@PathVariable String pid, Model model) {
        model.addAttribute("pid", pid);
        System.out.println("resume didn't uploaded successfully: " + pid);
        return "failed";
    }

    @PostMapping("/getAllJobs")
    public ResponseEntity<String> getJobs(@RequestParam String id) throws JsonProcessingException, SQLException, ClassNotFoundException {
        System.out.println("get All jobs");
        String response=candidateService.getAllJobs(id);
//        System.out.println("");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/addApplication")
    public String addApplication(@RequestParam (value = "j_id") String j_id, @RequestParam( value = "c_id") String c_id,
                                 @RequestParam (value = "status") String status, Model model) throws SQLException, ClassNotFoundException {
        Application application = new Application();
        Candidate candidate = new Candidate();
        candidate.setEmail(c_id);
        application.setC_id(candidate.getEmail());
        Job job = new Job();
        job.setJid(j_id);
        application.setJob_id(job.getJid());
        application.setA_id(1L);
        application.setStatus(status);

        String sts = candidateService.checkCandidateApplied(c_id,j_id);
        System.out.println("sts>>"+sts);
        if(sts.equalsIgnoreCase("notExist")) {
            System.out.println("application info::" + application);
            applicationService.saveApplication(application);
            model.addAttribute("status", "success");
            model.addAttribute("message", "Application Successfully submitted");
            return "viewSingleJob";
        }
        else {
            model.addAttribute("status", "failed");
            model.addAttribute("message", "You already Applied for this Job");
            return "viewSingleJob";
        }
    }
    @GetMapping("/getSingleJob")
    public String getSingleJob(@RequestParam String cid, @RequestParam String id,Model model)
    {
        model.addAttribute("cid",cid);
        model.addAttribute("id",id);
        System.out.println("c_id ::"+cid);
        System.out.println("j_id ::"+id);
        return "viewSingleJob";
    }

    @PostMapping("/postSingleJob")
    public ResponseEntity<String> postSingleJob(@RequestParam String id) throws JsonProcessingException, SQLException, ClassNotFoundException{
        System.out.println("single job");
        String response = candidateService.getSingleJob(id);
        return ResponseEntity.ok(response);
    }
}
