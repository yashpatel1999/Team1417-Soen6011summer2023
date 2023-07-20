package com.application.careerserviceapplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employer")
public class Employer {

    @Id
    @Column(name="Employer_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator = "id_Sequence")
    private Long e_id;

    @Column(name="fname")
    private String firstName;

    @Column(name="lname")
    private String lastName;

    @Column(name="email_id")
    private String email;

    @Column(name="company_name")
    private String company_name;

    @Column(name="mbile_no")
    private long mobile;

    public String toString() {
        return e_id + " ~ " + firstName + " ~ " + lastName + " ~ " + company_name + " ~ " + mobile + " ~ " + email;
    }

    public Long getE_id() {
        return e_id;
    }

    public void setE_id(Long e_id) {
        this.e_id = e_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
}
