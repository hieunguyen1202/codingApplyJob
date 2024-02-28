package com.swp.server.services.favorite_job;

import com.swp.server.dto.Favorite_JobDTO;
import com.swp.server.entities.Account;
import com.swp.server.entities.Favorite_Job;
import com.swp.server.entities.Job;
import com.swp.server.repository.FavoriteJobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoriteJobServiceImpl implements FavoriteJobService{
    @Autowired
    private FavoriteJobRepo favoriteJobRepo;
    @Override
    public ResponseEntity<?> favoriteJob(Favorite_JobDTO favorite_jobDTO) {
        try {
            Optional<Favorite_Job> findAccountId = favoriteJobRepo.findFirstByAccount_Id(favorite_jobDTO.getAccountId());
            Account account = findAccountId.get().getAccount();
            Optional<Favorite_Job> findJobId = favoriteJobRepo.findFirstByJob_Id(favorite_jobDTO.getJobId());
            Job job  = findJobId.get().getJob();
            if(findAccountId.isEmpty()){
                Map<String, String> error = new HashMap<>();
                error.put("error", "Account Id not found!");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
//            System.out.println("account id= " + account.getId());
//            System.out.println("job id= " + job.getId());
            Favorite_Job favoriteJob = new Favorite_Job();
            favoriteJob.setAccount(account);
            favoriteJob.setJob(job);
Favorite_Job newFavoriteJob = favoriteJobRepo.save(favoriteJob);
            Map<String, String> success = new HashMap<String, String>();
            success.put("success", "Added a job to your favorites successfully !!!");
    return  new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        }catch (Exception e){
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<?> viewFavoriteJob(int accountId) {
        List<Favorite_Job> favoriteJobs = favoriteJobRepo.findByAccount_Id(accountId);
        if (favoriteJobs.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "No favorite jobs found for the given account ID!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            Map<String, Object> success = new HashMap<>();
            success.put("success", "Get favorite jobs successfully!");

            List<Favorite_JobDTO> responseDTOs = new ArrayList<>();
            for (Favorite_Job favoriteJob : favoriteJobs) {
                Favorite_JobDTO responseDTO = new Favorite_JobDTO();
                responseDTO.setId(favoriteJob.getId());
                responseDTO.setAccountId(favoriteJob.getAccount().getId());
                responseDTO.setJobId(favoriteJob.getJob().getId());
                responseDTOs.add(responseDTO);
            }

            success.put("metadata", responseDTOs);
            return ResponseEntity.ok(success);
        }
    }

    @Override
    public ResponseEntity<?> deleteFavoriteJob(int favoriteJobId) {
        // Assuming you have a service or repository to handle the deletion
        Optional<Favorite_Job> deleteJob = favoriteJobRepo.findFirstById(favoriteJobId);

        if (deleteJob.isPresent()) {
            favoriteJobRepo.delete(deleteJob.get());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Favorite job deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite job not found");
        }
    }
}
