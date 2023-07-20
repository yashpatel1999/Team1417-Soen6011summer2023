package com.application.careerserviceapplication.services;

import com.application.careerserviceapplication.models.Employer;
import com.application.careerserviceapplication.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

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

}
