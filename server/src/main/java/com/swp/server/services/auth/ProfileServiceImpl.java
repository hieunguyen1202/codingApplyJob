package com.swp.server.services.auth;

import com.swp.server.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Override
    public ResponseEntity<?> createProfile(ProfileDTO profileDTO){
        return null;
    }

}
