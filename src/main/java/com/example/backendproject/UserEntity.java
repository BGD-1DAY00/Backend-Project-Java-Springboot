package com.example.backendproject;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String password;
    private String role;

    private Boolean applicant = false;
    private Boolean recruiter = false;
    private Boolean admin = false;


    public UserEntity(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getApplicant() {
        return applicant;
    }

    public void setApplicant(Boolean applicant) {
        this.applicant = applicant;
    }

    public Boolean getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Boolean recruiter) {
        this.recruiter = recruiter;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
//All setters and Getters are generated via the @Data annotation

//    public void setApplicant(Boolean applicant) {
//        this.applicant = applicant;
//    }
//
//    public void setRecruiter(Boolean recruiter) {
//        this.recruiter = recruiter;
//    }
//
//    public void setAdmin(Boolean admin) {
//        this.admin = admin;
//    }
};
