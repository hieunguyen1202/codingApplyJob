package com.swp.server.services.job_application;

import com.swp.server.dto.*;
import com.swp.server.entities.*;
import com.swp.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
@Service
public class JobApplicationServiceImpl implements JobApplicationService{
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private JobApplyRepo jobApplyRepo;
    @Override
    public ResponseEntity<?> applyJob(@ModelAttribute JobApplyDTO jobApplyDTO) {
        try {
            System.out.println("email:" + jobApplyDTO.getEmail());
//             System.out.println("apply id:" + jobApplyDTO.getId());
            Optional<Account> findAccountByEmail = accountRepo.findFirstByEmail(jobApplyDTO.getEmail());
            Optional<Job> findJobById = jobRepo.findById(jobApplyDTO.getJob_Id());

            if (findAccountByEmail.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Account not found");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            if (findJobById.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Job not found");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            Account account = findAccountByEmail.get();
            System.out.println("account id:" + account.getId());
            Optional<Profile> findProfileByAccountId = profileRepo.findFirstByAccount_id(account.getId());
            Profile profile = findProfileByAccountId.get();
            Job job = findJobById.get();
            System.out.println("job id:" + job.getId());

            // Check if the user has already applied for the same job more than three times
            int appliedTimes = jobApplyRepo.countByJobAndEmail(jobApplyDTO.getJob_Id(), jobApplyDTO.getEmail());
            System.out.println("Count apply: " + appliedTimes);
            if (appliedTimes >= 3) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "You have already applied for this job more than three times");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            Job_Application jobApplication = new Job_Application();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            jobApplication.setEmail(jobApplyDTO.getEmail());
            jobApplication.setFull_Name(profile.getFirstName() + profile.getLastName());
            jobApplication.setStatus("Pending");
            jobApplication.setCover_Letter(jobApplyDTO.getCover_Letter());
            jobApplication.setCreated_At(sqlDate);
            jobApplication.setPhone_Number(profile.getPhoneNumber());
            jobApplication.setCV(profile.getCV());
            jobApplication.setJob(job);
            jobApplication.setAccount(account);
            Job_Application newJob_application = jobApplyRepo.save(jobApplication);
            Map<String, String> success = new HashMap<String, String>();
            success.put("success", "Apply a job successfully !!!");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateJobApplication(@ModelAttribute UpdateJobApplyDTO updateJobApplyDTO) {
        try {
            Optional<Job_Application> findJobApplyById = jobApplyRepo.findFirstById(updateJobApplyDTO.getId());
            if (findJobApplyById.isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Job application not found");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            Job_Application jobApplication = findJobApplyById.get();
            System.out.println("jobApply ID: " + jobApplication.getId());
            System.out.println("jobApply status before: " + jobApplication.getStatus());
            if(updateJobApplyDTO.getStatus() != null){
                jobApplication.setStatus(updateJobApplyDTO.getStatus()); // Update the status field
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                jobApplication.setUpdated_At(sqlDate);
            }
            System.out.println("jobApply status after: " + jobApplication.getStatus());

            // Save the updated job application
            jobApplyRepo.save(jobApplication);
            Map<String, String> success = new HashMap<String, String>();
            success.put("success", "Update a job application successfully !!!");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> viewJobApplicationByAccountId(int accountId) {
        try {
           Job_Application jobApply = new Job_Application();
           ViewJobApplyDTO viewJobApplyDTO = new ViewJobApplyDTO();
            Optional<Job_Application> findJobAppliByAccountId = jobApplyRepo.findFirstByAccountId(accountId);
Optional<Account> findAccountId = accountRepo.findById(accountId);
if(findAccountId.isEmpty()){
    Map<String, Object> error = new HashMap<>();
    error.put("error", "AccountId not found");

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
            if (findJobAppliByAccountId.isPresent()) {
                jobApply = findJobAppliByAccountId.get();
                viewJobApplyDTO.setId(jobApply.getId());
                viewJobApplyDTO.setEmail(jobApply.getEmail());
                viewJobApplyDTO.setFull_Name(jobApply.getFull_Name());
                viewJobApplyDTO.setCV(jobApply.getCV());
                viewJobApplyDTO.setStatus(jobApply.getStatus());
                viewJobApplyDTO.setCover_Letter(jobApply.getCover_Letter());
                viewJobApplyDTO.setJob(jobApply.getJob());
                viewJobApplyDTO.setAccount(jobApply.getAccount());
                viewJobApplyDTO.setCreated_At(jobApply.getCreated_At());
                viewJobApplyDTO.setUpdated_At(jobApply.getUpdated_At());
                viewJobApplyDTO.setPhone_Number(jobApply.getPhone_Number());
                Map<String, Object> success = new HashMap<>();
                return ResponseEntity.ok(viewJobApplyDTO);
            } else {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Job application not found");

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");

            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> viewJobApplication() {
        try {
            List<Job_Application> jobApplicationList = jobApplyRepo.findAll();
            List<ViewJobApplyDTO> viewJobApplyDTOList = new ArrayList<>();
            if (jobApplicationList.isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "No job applications found");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            for (Job_Application jobApplication : jobApplicationList){
                ViewJobApplyDTO viewJobApplyDTO = new ViewJobApplyDTO();
                viewJobApplyDTO.setId(jobApplication.getId());
                viewJobApplyDTO.setEmail(jobApplication.getEmail());
                viewJobApplyDTO.setFull_Name(jobApplication.getFull_Name());
                viewJobApplyDTO.setCV(jobApplication.getCV());
                viewJobApplyDTO.setStatus(jobApplication.getStatus());
                viewJobApplyDTO.setCover_Letter(jobApplication.getCover_Letter());
                viewJobApplyDTO.setJob(jobApplication.getJob());
                viewJobApplyDTO.setAccount(jobApplication.getAccount());
                viewJobApplyDTO.setCreated_At(jobApplication.getCreated_At());
                viewJobApplyDTO.setUpdated_At(jobApplication.getUpdated_At());
                viewJobApplyDTO.setPhone_Number(jobApplication.getPhone_Number());
                viewJobApplyDTOList.add(viewJobApplyDTO);
            }
            return ResponseEntity.ok(viewJobApplyDTOList);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
