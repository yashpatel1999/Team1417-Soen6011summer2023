package com.application.careerserviceapplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate")
public class Candidate {

    @Id
    @Column(name="candiate_id",unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator = "id_Sequence")
    private Long c_id;

    @Column(name="fname")
    private String firstName;

    @Column(name="lname")
    private String lastName;

    @Column(name="address")
    private String address;

    @Column(name="email_id")
    private String email;

    @Column(name="date_of_birth")
    private Date birthday;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Application> application;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public String toString() {
        return c_id + " ~ " + firstName + " ~ " + lastName + " ~ " + address + " ~ " + email;
    }

    public Long getCid() {
        return c_id;
    }

    public void setMid(String mid) {
        this.c_id = c_id;
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

    public void setLname(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Candidate(String c_id) {
//        this.c_id ="1";
//        this.firstName = "test";
//        this.lastName="test";
//        this.address="hh";
//        this.birthday= new Date();
//        this.email="test";
//
//    }
}
