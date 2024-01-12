package com.swp.server.repository;

import com.swp.server.entities.Account;
import com.swp.server.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile,Integer> {
    Optional<Profile> findAllByPhoneNumber(String phoneNumber);

    Optional<Profile> findOneByAccount_Id(int i);

    Optional<Profile> findFirstByAccount_id(int i);
}
