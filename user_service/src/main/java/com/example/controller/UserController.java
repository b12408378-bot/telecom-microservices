package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.model.User;
import com.example.repository.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	public UserRepository repo;
	
	
	@GetMapping("/userlist")
	public List<User> displayUser(){
		return repo.findAll();
	}
	
	
	@PostMapping
	public User findUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	
	@GetMapping("/userlist/{user_id}")
	public Optional<User> getUserById(@PathVariable int user_id){
		return repo.findById(user_id);
	}
	
}