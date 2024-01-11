package com.swp.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.server.dto.ChangePasswordDTO;
import com.swp.server.dto.LoginDTO;
import com.swp.server.dto.OTPCodeAndEmailDTO;
import com.swp.server.dto.ReceiverOtpCode;
import com.swp.server.dto.SignUpDTO;
import com.swp.server.services.auth.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpDTO signUpDTO) {
		return authService.createAccount(signUpDTO);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
		return authService.login(loginDTO);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		return authService.changePassword(changePasswordDTO);
	}

	// send otp code
	@PostMapping("/sendOtpCode")
	public ResponseEntity<?> sendOtpCode(@RequestBody ReceiverOtpCode receiverOtpCode) {
		return authService.sendOtpCodeToUser(receiverOtpCode);
	}

	// verify account
	@PostMapping("/verifyAccount")
	public ResponseEntity<?> verifyAccount(@RequestBody OTPCodeAndEmailDTO codeAndEmailDTO) {
		return authService.verifyAccount(codeAndEmailDTO);
	}

}
