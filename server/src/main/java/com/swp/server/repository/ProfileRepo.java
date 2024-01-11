package com.swp.server.repository;

import com.swp.server.entities.Account;
import com.swp.server.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile,Integer> {
    Optional<Profile> findFirstByPhoneNumber(String phoneNumber);

    Optional<Profile> findFirstByAccountId(int i);

    Optional<Profile> findFirstByAccount_id(int i);
}
