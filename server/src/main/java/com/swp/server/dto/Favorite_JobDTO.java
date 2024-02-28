package com.swp.server.dto;

import com.swp.server.entities.Account;
import com.swp.server.entities.Job;

public class Favorite_JobDTO {
    private int id;
    private int jobId;
    private int accountId;

    public Favorite_JobDTO() {
    }

    public Favorite_JobDTO(int id, int jobId, int accountId) {
        this.id = id;
        this.jobId = jobId;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}