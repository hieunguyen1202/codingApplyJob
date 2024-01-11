package com.swp.server.controller;

import com.swp.server.dto.ProfileDTO;
import com.swp.server.services.auth.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @PostMapping("/createprofile")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDTO profileDTO) {
        return profileService.createProfile(profileDTO);
    }

}
