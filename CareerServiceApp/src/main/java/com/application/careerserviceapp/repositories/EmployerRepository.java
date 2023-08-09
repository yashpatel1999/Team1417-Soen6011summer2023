package com.application.careerserviceapplication.repositories;

import com.application.careerserviceapplication.models.Employer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,String> {
    @Query("SELECT us from Employer us where us.email=?1")
    public Employer getEmployerByemail(String email);
}
