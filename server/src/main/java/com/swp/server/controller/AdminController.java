package com.swp.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
@SecurityRequirement(name = "Authorization")
public class AdminController {

	@GetMapping("/hello")
	public ResponseEntity<?> hello() {
		return ResponseEntity.ok("Hello world");
	}

}
