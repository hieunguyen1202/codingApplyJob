package com.swp.server.services.job_application;

import com.swp.server.dto.JobApplyDTO;
import com.swp.server.dto.UpdateJobApplyDTO;
import com.swp.server.dto.UpdateJobCategoryDTO;
import org.springframework.http.ResponseEntity;

public interface JobApplicationService {
    public ResponseEntity<?> applyJob(JobApplyDTO jobApplyDTO);
   public ResponseEntity<?> updateJobApplication(UpdateJobApplyDTO updateJobApplyDTO);
    public ResponseEntity<?> viewJobApplicationByAccountId(int accountId);
    public ResponseEntity<?> viewJobApplication();
}
