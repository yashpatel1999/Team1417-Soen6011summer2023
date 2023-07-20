package com.application.careerserviceapplication.repositories;

import com.application.careerserviceapplication.models.Candidate;
import com.application.careerserviceapplication.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {

}
