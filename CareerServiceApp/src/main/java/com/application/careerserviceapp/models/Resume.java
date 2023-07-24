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
@Table(name = "resumedetails")
public class Resume {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getCno() {
        return cno;
    }

    public void setCno(long cno) {
        this.cno = cno;
    }



    public String getWorkex() {
        return workex;
    }

    public void setWorkex(String workex) {
        this.workex = workex;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Id
    @Column(name="email_id")
    private String email;

    @Column(name="fname")
    private String firstName;

    @Column(name="lname")
    private String lastName;

    @Column(name="date_of_birth")
    private Date birthday;

    @Column(name="c_no")
    private long cno;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column(name="education")
    private String education;

    @Column(name="workex")
    private String workex;

    @Column(name="projects")
    private String projects;

    @Column(name="skills")
    private String skills;






}