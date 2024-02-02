package com.swp.server.services.job;

import com.swp.server.dto.JobDTO;
import org.springframework.http.ResponseEntity;

public interface JobService {
    public ResponseEntity<?> createJob(JobDTO jobDTO);

    public ResponseEntity<?> viewJob();

    public ResponseEntity<?> updateJob(int Id, JobDTO jobDTO);

    public ResponseEntity<?> getJobInforById(int Id, JobDTO jobDTO);
}
