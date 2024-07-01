package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepository;

@Service
public class AuthenticationService {

	@Autowired
	private LoginRepository loginRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(User request) {
		User user = new User();
		user.setUserName(request.getUsername());
		user.setEmail(request.getEmail());
		user.setMobileNumber(request.getMobileNumber());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		
		user = loginRepo.save(user);
		
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse authenticate(User request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(), 
				request.getPassword()));
		
		User user = loginRepo.findByUserName(request.getUsername()).orElseThrow();
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}
}
