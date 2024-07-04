package com.example.demo.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoController {
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
		return ResponseEntity.ok(authService.register(request));
	}

	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
	@GetMapping("/users")
	public Collection<User> getUsers(){
		return loginService.getUsers();
	}
	
}