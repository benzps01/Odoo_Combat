package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LoginRepository;

@Service
public class LoginService implements UserDetailsService {
	
	
	@Autowired
	public LoginRepository loginRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return loginRepo.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found!!"));
	}
	
	
}
