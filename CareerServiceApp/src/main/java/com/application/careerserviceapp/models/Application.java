package com.application.careerserviceapplication.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Table(name = "application_details")
public class Application {

    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy=GenerationType.AUTO,generator = "id_Sequence")
    private Long a_id;

    @Column(name = "status")
    private String status;

    @Column(name ="c_id")
    private String c_id;

    @Column(name = "job_id")
    private String job_id;

//    @ManyToOne
////    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    @JoinColumn(name = "job_id")
//    private Job job;
//
//    @ManyToOne
////    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    @JoinColumn(name = "c_id")
//    private Candidate candidate;

    public Long getA_id() {
        return a_id;
    }

    public void setA_id(Long a_id) {
        this.a_id = a_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    //    public Job getJob() {
//        return job;
//    }
//
//    public void setJob(Job job) {
//        this.job = job;
//    }
//
//    public Candidate getCandidate() {
//        return candidate;
//    }
//
//    public void setCandidate(Candidate candidate) {
//        this.candidate = candidate;
//    }
}
