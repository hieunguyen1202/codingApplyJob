package com.swp.server.services.auth;

import com.swp.server.dto.AccountDTO;

import com.swp.server.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
	public ResponseEntity<?> createProfile(ProfileDTO profileDTO);

	public ResponseEntity<?> viewProfile(ProfileDTO profileDTO);

	public ResponseEntity<?> viewProfileByEmail(AccountDTO emailDTO);

	public ResponseEntity<?> getAllProfile();
}
