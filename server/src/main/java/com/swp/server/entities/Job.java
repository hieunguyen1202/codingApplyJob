package com.swp.server.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @ManyToOne
    @JoinColumn(name = "Category_Id", referencedColumnName = "Id")
    private Job_Category job_category;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Job_Application> jobApplications;
    @Column(name = "Career_Level")
    private String Career_Level;
    @Column(name = "Experience")
    private Integer Experience;
    @Column(name = "Offer_Salary")
    private String Offer_Salary;
    @Column(name = "Qualification")
    private String Qualification;
    @Column(name = "Job_Type")
    private String Job_Type;
    @Column(name = "Description")
    private String Description;
    @Column(name = "Apply_Before")
    private Date Apply_Before;
    @Column(name = "Address")
    private String Address;
    @Column(name = "Created_At")
    private Date Created_At;
    @Column(name = "Updated_At")
    private Date Updated_At;

    public Job() {
    }

    public Job(int id, Job_Category job_category, List<Job_Application> jobApplications, String career_Level, Integer experience, String offer_Salary, String qualification, String job_Type, String description, Date apply_Before, String address, Date created_At, Date updated_At) {
        Id = id;
        this.job_category = job_category;
        this.jobApplications = jobApplications;
        Career_Level = career_Level;
        Experience = experience;
        Offer_Salary = offer_Salary;
        Qualification = qualification;
        Job_Type = job_Type;
        Description = description;
        Apply_Before = apply_Before;
        Address = address;
        Created_At = created_At;
        Updated_At = updated_At;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Job_Category getJob_category() {
        return job_category;
    }

    public void setJob_category(Job_Category job_category) {
        this.job_category = job_category;
    }

    public List<Job_Application> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<Job_Application> jobApplications) {
        this.jobApplications = jobApplications;
    }

    public String getCareer_Level() {
        return Career_Level;
    }

    public void setCareer_Level(String career_Level) {
        Career_Level = career_Level;
    }

    public Integer getExperience() {
        return Experience;
    }

    public void setExperience(Integer experience) {
        Experience = experience;
    }

    public String getOffer_Salary() {
        return Offer_Salary;
    }

    public void setOffer_Salary(String offer_Salary) {
        Offer_Salary = offer_Salary;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getJob_Type() {
        return Job_Type;
    }

    public void setJob_Type(String job_Type) {
        Job_Type = job_Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getApply_Before() {
        return Apply_Before;
    }

    public void setApply_Before(Date apply_Before) {
        Apply_Before = apply_Before;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getCreated_At() {
        return Created_At;
    }

    public void setCreated_At(Date created_At) {
        Created_At = created_At;
    }

    public Date getUpdated_At() {
        return Updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        Updated_At = updated_At;
    }
}