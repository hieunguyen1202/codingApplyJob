package com.swp.server.services.job;

import com.swp.server.dto.JobCategoryDTO;
import com.swp.server.dto.JobDTO;
import com.swp.server.dto.UpdateJobCategoryDTO;

import org.springframework.http.ResponseEntity;

public interface JobService {
	public ResponseEntity<?> createJob(JobDTO jobDTO);

	public ResponseEntity<?> viewJob();

	public ResponseEntity<?> updateJob(int Id, JobDTO jobDTO);

	public ResponseEntity<?> getJobInforById(int Id);

	public ResponseEntity<?> createJobCategory(JobCategoryDTO jobDTO);

	ResponseEntity<?> updateJobCategory(UpdateJobCategoryDTO jobDTO);

	public ResponseEntity<?> deleteJobCategory(int jobId);

	public ResponseEntity<?> deleteJob(int jobId);

	ResponseEntity<?> viewJobCategory();

	public ResponseEntity<?> getAllBranch();
}
