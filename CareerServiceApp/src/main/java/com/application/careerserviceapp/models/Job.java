package com.application.careerserviceapplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name="postedBy")
    private String postedBy;

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

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}
