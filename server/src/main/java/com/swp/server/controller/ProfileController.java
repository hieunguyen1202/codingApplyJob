package com.swp.server.controller;

import com.swp.server.dto.AccountDTO;
import com.swp.server.dto.ProfileDTO;
import com.swp.server.dto.UpdateProfileDTO;
import com.swp.server.entities.Profile;
import com.swp.server.repository.ProfileRepo;
import com.swp.server.services.auth.ProfileService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class ProfileController {

//    public Resource loadFileAsResource(String fileName) {
//        try {
//            Resource resource = new UrlResource(fileName.toUri());
//            if(resource.exists()) {
//                return resource;
//            } else {
//                throw new MyFileNotFoundException("File not found " + fileName);
//            }
//        } catch (MalformedURLException ex) {
//            throw new MyFileNotFoundException("File not found " + fileName, ex);
//        }
//    }

	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	ProfileService profileService;

	@PutMapping("/createprofile")
	public ResponseEntity<?> createProfile(@ModelAttribute ProfileDTO profileDTO) {
		return profileService.createProfile(profileDTO);
	}

	// view profile
	@GetMapping("/profile")
	public ResponseEntity<?> viewProfile(@ModelAttribute ProfileDTO profileDTO) {
		return profileService.viewProfile(profileDTO);
	}

	// view profile
	@PostMapping("/viewDetail")
	public ResponseEntity<?> viewProfileByEmail(@RequestBody AccountDTO account) {
		return profileService.viewProfileByEmail(account);
	}

	// update cv
	@PutMapping("/update-profile")
	public ResponseEntity<?> updateProfileByEmail(@ModelAttribute UpdateProfileDTO profileDTO){
		return  profileService.updateProfileByEmail(profileDTO);
	}


	// dowload cv
	@GetMapping("/downloadFile/{accountId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String accountId, HttpServletRequest request) {
		Optional<Profile> profile = profileRepo.findFirstByAccount_id(Integer.parseInt(accountId));

		if (!profile.isPresent()) {
			// Handle case when profile is not found
			return ResponseEntity.notFound().build();
		}

		// Convert varbinary(MAX) to byte array
		byte[] fileBytes = profile.get().getCV();

		ByteArrayResource resource = new ByteArrayResource(fileBytes);

		// Set the content type specifically for PDF files
		String contentType = "application/pdf";

		String cleanedFileName = cleanHeaderField(profile.get().getStringCV());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(contentType));
		headers.setContentDispositionFormData("attachment", cleanedFileName);

		return ResponseEntity.ok().headers(headers).body(resource);
	}

	private String cleanHeaderField(String fieldValue) {
		// Remove CR/LF characters from the header field value
		return fieldValue.replace("\r", "").replace("\n", "");
	}
}
