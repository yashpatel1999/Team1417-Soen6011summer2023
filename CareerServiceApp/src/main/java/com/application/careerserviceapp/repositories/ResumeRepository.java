package com.application.careerserviceapplication.repositories;

import com.application.careerserviceapplication.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume,String> {

}
