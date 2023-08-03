package com.application.careerserviceapplication.repositories;

import com.application.careerserviceapplication.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
