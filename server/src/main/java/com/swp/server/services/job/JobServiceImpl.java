package com.swp.server.services.job;

import com.swp.server.dto.JobDTO;
import com.swp.server.entities.Job;
import com.swp.server.entities.Job_Category;
import com.swp.server.repository.JobApplyRepo;
import com.swp.server.repository.JobCategoryRepo;
import com.swp.server.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobApplyRepo jobApplyRepo;

    @Autowired
    private JobCategoryRepo jobCategoryRepo;

    @Autowired
    private JobRepo jobRepo;

    @Override
    public ResponseEntity<?> createJob(JobDTO jobDTO) {
        try {
            Job job = new Job();
            Optional<Job_Category> jobCategoryOptional = jobCategoryRepo.findById(jobDTO.getCategory_Id());
            if (jobCategoryOptional.isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Job category not found");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            } else {
                job.setJob_category(jobCategoryOptional.get());
            }
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            job.setJobApplications(null);
            if(jobDTO.getCareer_Level().equals("Manager") || jobDTO.getCareer_Level().equals("Fresher")
                    || jobDTO.getCareer_Level().equals("Junior") || jobDTO.getCareer_Level().equals("Senior"))
            {
                job.setCareer_Level(jobDTO.getCareer_Level());
            }
            else{
                job.setCareer_Level("Others");
            }
            if(jobDTO.getExperience() >= 0){
                job.setExperience(jobDTO.getExperience());
            }
            else{
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Please input experience >= 0");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            if(jobDTO.getOffer_Salary().equals("0-$1000") || jobDTO.getOffer_Salary().equals("$1000-$2000")
                    || jobDTO.getOffer_Salary().equals("$2000-$3000") || jobDTO.getOffer_Salary().equals("$3000-$5000")
                    || jobDTO.getQualification().equals("$5000++"))
            {
                job.setOffer_Salary(jobDTO.getOffer_Salary());
            }
            else {
                job.setOffer_Salary("Negotiable");
            }
            if(jobDTO.getQualification().equals("Certificate") || jobDTO.getQualification().equals("Diploma")
                    || jobDTO.getQualification().equals("Associate Degree") || jobDTO.getQualification().equals("Bachelor Degree")
                    || jobDTO.getQualification().equals("Master’s Degree") || jobDTO.getQualification().equals("Doctorate Degree"))
            {
                job.setQualification(jobDTO.getQualification());
            }
            else {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Wrong input");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            job.setJob_Type(jobDTO.getJob_Type());
            job.setDescription(jobDTO.getDescription());
            job.setApply_Before(jobDTO.getApply_Before());
            job.setAddress(jobDTO.getAddress());
            job.setCreated_At(sqlDate);
            job.setUpdated_At(null);
            jobRepo.save(job);
            Map<String, String> success = new HashMap<String, String>();
            success.put("success", "Job created successfully !!!");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> viewJob() {
        List<JobDTO> jobDTOS = new ArrayList<>();
        JobDTO jobDTO = new JobDTO();
        List<Job> jobOptional = jobRepo.findAll().stream().toList();
        if (jobOptional.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Job is empty");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        for (Job job: jobOptional) {
            jobDTO.setCategory_Id(job.getJob_category().getId());
            jobDTO.setCareer_Level(job.getCareer_Level());
            jobDTO.setExperience(job.getExperience());
            jobDTO.setOffer_Salary(job.getOffer_Salary());
            jobDTO.setQualification(job.getQualification());
            jobDTO.setJob_Type(job.getJob_Type());
            jobDTO.setDescription(job.getDescription());
            jobDTO.setApply_Before(job.getApply_Before());
            jobDTO.setAddress(job.getAddress());
            jobDTOS.add(jobDTO);
        }
        return ResponseEntity.ok(jobDTOS);
    }

    @Override
    public ResponseEntity<?> updateJob(int Id, JobDTO jobDTO) {
        try{
        Job job = new Job();
        Optional<Job> jobOptional = jobRepo.findById(Id);
        if(jobOptional.isEmpty()){
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Job not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        else{
            job = jobOptional.get();
        }
        Optional<Job_Category> jobCategoryOptional = jobCategoryRepo.findById(jobDTO.getCategory_Id());
        if (jobCategoryOptional.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Job category not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            job.setJob_category(jobCategoryOptional.get());
        }
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        job.setJobApplications(null);
        if(jobDTO.getCareer_Level().equals("Manager") || jobDTO.getCareer_Level().equals("Fresher")
                || jobDTO.getCareer_Level().equals("Junior") || jobDTO.getCareer_Level().equals("Senior"))
        {
            job.setCareer_Level(jobDTO.getCareer_Level());
        }
        else{
            job.setCareer_Level("Others");
        }
        if(jobDTO.getExperience() >= 0){
            job.setExperience(jobDTO.getExperience());
        }
        else{
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Please input experience >= 0");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if(jobDTO.getOffer_Salary().equals("0-$1000") || jobDTO.getOffer_Salary().equals("$1000-$2000")
                || jobDTO.getOffer_Salary().equals("$2000-$3000") || jobDTO.getOffer_Salary().equals("$3000-$5000")
                || jobDTO.getQualification().equals("$5000++"))
        {
            job.setOffer_Salary(jobDTO.getOffer_Salary());
        }
        else {
            job.setOffer_Salary("Negotiable");
        }
        if(jobDTO.getQualification().equals("Certificate") || jobDTO.getQualification().equals("Diploma")
                || jobDTO.getQualification().equals("Associate Degree") || jobDTO.getQualification().equals("Bachelor Degree")
                || jobDTO.getQualification().equals("Master’s Degree") || jobDTO.getQualification().equals("Doctorate Degree"))
        {
            job.setQualification(jobDTO.getQualification());
        }
        else {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Wrong input");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        job.setJob_Type(jobDTO.getJob_Type());
        job.setDescription(jobDTO.getDescription());
        job.setApply_Before(jobDTO.getApply_Before());
        job.setAddress(jobDTO.getAddress());
        job.setUpdated_At(sqlDate);
        jobRepo.save(job);
        Map<String, String> success = new HashMap<String, String>();
        success.put("success", "Job updated successfully !!!");
        return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getJobInforById(int Id, JobDTO jobDTO){
        Job job = new Job();
        Optional<Job> jobOptional = jobRepo.findById(Id);
        if(jobOptional.isEmpty()){
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Job not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        else{
            job = jobOptional.get();
            jobDTO.setCategory_Id(job.getJob_category().getId());
            jobDTO.setCareer_Level(job.getCareer_Level());
            jobDTO.setExperience(job.getExperience());
            jobDTO.setOffer_Salary(job.getOffer_Salary());
            jobDTO.setQualification(job.getQualification());
            jobDTO.setJob_Type(job.getJob_Type());
            jobDTO.setDescription(job.getDescription());
            jobDTO.setApply_Before(job.getApply_Before());
            jobDTO.setAddress(job.getAddress());
            return ResponseEntity.ok(jobDTO);
        }
    }
}
