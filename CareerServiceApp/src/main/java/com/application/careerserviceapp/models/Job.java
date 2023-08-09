package com.application.careerserviceapplication.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Table(name = "Job_Details")
public class Job {

    @Id
    @Column(name = "job_id")
//    @GeneratedValue(strategy=GenerationType.IDENTITY,generator = "id_Sequence")
    private String jid;

    @Column(name = "job_title")
    private String title;

    @Column(name = "job_desc")
    private String desc;

    @Column(name = "job_posted")
    private Date jobPosted;

    @Column(name="company_name")
    private String companyName;

    @Column(name="company_location")
    private String companyLocation;

    @Column(name = "deadline")
    private Date appDeadline;

    @ManyToOne
    @JoinColumn(name="postedBy")
    Employer employer;

//    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "")
//    private List<Application> application = new ArrayList<>();

    public String getId() {
        return jid;
    }

    public void setId(String id) {
        this.jid = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getJobPosted() {
        return jobPosted;
    }

    public void setJobPosted(Date jobPosted) {
        this.jobPosted = jobPosted;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public Employer getPostedBy() {
        return employer;
    }

    public void setPostedBy(Employer employer) {
        this.employer=employer;
    }

    public Date getAppDeadline() {
        return appDeadline;
    }

    public void setAppDeadline(java.sql.Date  appDeadline) {
        this.appDeadline = appDeadline;
    }
}
