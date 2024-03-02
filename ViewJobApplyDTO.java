package com.swp.server.dto;

import com.swp.server.entities.Account;
import com.swp.server.entities.Job;

import java.sql.Date;

public class ViewJobApplyDTO {
    private int Id;
    private Account account;
    private Job job;
    private String status;
    private String full_Name;
    private String email;
    private String phone_Number;
    private byte[] CV;
    private String cover_Letter;
    private Date created_At;
    private Date updated_At;

    public ViewJobApplyDTO() {
    }

    public ViewJobApplyDTO(int id, Account account, Job job, String status, String full_Name, String email, String phone_Number, byte[] CV, String cover_Letter, Date created_At, Date updated_At) {
        Id = id;
        this.account = account;
        this.job = job;
        this.status = status;
        this.full_Name = full_Name;
        this.email = email;
        this.phone_Number = phone_Number;
        this.CV = CV;
        this.cover_Letter = cover_Letter;
        this.created_At = created_At;
        this.updated_At = updated_At;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public byte[] getCV() {
        return CV;
    }

    public void setCV(byte[] CV) {
        this.CV = CV;
    }

    public String getCover_Letter() {
        return cover_Letter;
    }

    public void setCover_Letter(String cover_Letter) {
        this.cover_Letter = cover_Letter;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }
}
