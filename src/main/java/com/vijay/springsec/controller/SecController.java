package com.vijay.springsec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.springsec.entity.Users;
import com.vijay.springsec.entity.repo.UsersRepo;

@RestController
public class SecController {
	@Autowired
	private UsersRepo repo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@GetMapping("/test")
	public String test() {
		return "Success!";
	}

	@GetMapping("/test1")
	public String test1() {
		
		return "Success1!";
	}
	@GetMapping("/test2")
	public String test2() {
		
		return "Success2!";
	}
	@PostMapping("/signup")
	public String signup(@RequestBody Users user) {
		System.out.println("signup");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
		return "Success1!";
	}
}
