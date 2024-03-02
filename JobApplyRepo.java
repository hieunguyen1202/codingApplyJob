package com.swp.server.repository;

import com.swp.server.dto.JobApplyDTO;
import com.swp.server.entities.Job;
import com.swp.server.entities.Job_Application;
import com.swp.server.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface JobApplyRepo extends JpaRepository<Job_Application, Integer> {
    Optional<Job_Application> findByJob(Job job);
    Optional<Job_Application> findFirstById(int id);
    Optional<Job_Application> findFirstByAccountId(int id);

    @Query("SELECT COUNT(*) FROM Job_Application ja WHERE ja.job.id = :jobId AND ja.Email = :email")
    int countByJobAndEmail(@Param("jobId") Integer jobId, @Param("email") String email);
}