package com.application.careerserviceapplication.services;


import com.application.careerserviceapplication.models.Application;
import com.application.careerserviceapplication.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public void saveApplication(Application application)
    {
        try
        {
            System.out.println("saved application::"+ application);
            System.out.println(applicationRepository);
            applicationRepository.save(application);
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }
}
