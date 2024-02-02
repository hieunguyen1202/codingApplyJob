package com.swp.server.controller;

import com.swp.server.dto.JobDTO;
import com.swp.server.repository.JobApplyRepo;
import com.swp.server.repository.JobCategoryRepo;
import com.swp.server.repository.JobRepo;
import com.swp.server.services.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobApplyRepo jobApplyRepo;

    @Autowired
    private JobCategoryRepo jobCategoryRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private JobService jobService;

    @GetMapping("/viewJob")
    public ResponseEntity<?> viewJob(){
        return jobService.viewJob();
    }

    @PostMapping("/createJob")
    public ResponseEntity<?> createJob(@RequestBody JobDTO jobDTO){
        return jobService.createJob(jobDTO);
    }

    @GetMapping("/edit/{jobId}")
    public ResponseEntity<?> viewJobInfor(@PathVariable int jobId, JobDTO jobDTO){
        return jobService.getJobInforById(jobId,jobDTO);
    }

    @PutMapping("/edit/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable int jobId, @RequestBody JobDTO jobDTO){
        return jobService.updateJob(jobId,jobDTO);
    }
}
