package com.swp.server.services.auth;

import com.swp.server.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    public ResponseEntity<?> createProfile(ProfileDTO profileDTO);
}
