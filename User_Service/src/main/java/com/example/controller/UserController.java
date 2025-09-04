package com.example.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.model.User;
import com.example.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


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
	public User createUser(@RequestBody User user) {
	    return repo.save(user);
	}


	@GetMapping("/userlist/{user_id}")
	public Optional<User> getUserById(@PathVariable int user_id){
		return repo.findById(user_id);
	}
	
//	@GetMapping("/me")
//	public ResponseEntity<User> getCurrentUser(
//	        HttpSession session,
//	        @RequestParam(required = false) Integer overrideId) {
//
//	    Integer userId = (Integer) session.getAttribute("userId");
//
//	    // ✅ allow frontend to override for testing
//	    if (userId == null && overrideId != null) {
//	        userId = overrideId;
//	    }
//
//	    if (userId == null) {
//	        return ResponseEntity.status(401).build(); // no user found
//	    }
//
//	    return repo.findById(userId)
//	            .map(ResponseEntity::ok)
//	            .orElse(ResponseEntity.notFound().build());
//	}
	
	
	@GetMapping("/latest")
	public ResponseEntity<User> getLatestUser() {
	    return repo.findAll().stream()
	            .reduce((first, second) -> second) // get last
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}

	

	@GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        // Temporary until login MS is ready:
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            // fallback for testing → assume first user in DB
            userId = 152;
        }

        return repo.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
}