package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.dto.UserDTO;
import com.app.model.Payment;
import com.app.repository.PaymentRepository;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	public PaymentRepository repo;
	
	
//	@PostMapping("/pay")
//	public Payment createPayment(@RequestBody Payment payment) {
//	    return repo.save(payment);
//	}
	
	
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/pay")
	public Payment createPayment(@RequestBody Payment payment) {
	    // fetch latest user from user-service
	    UserDTO latestUser = restTemplate.getForObject(
	            "http://localhost:8080/user-service/users/latest",
	            UserDTO.class
	    );

	    if (latestUser == null) {
	        throw new RuntimeException("No user found");
	    }

	    payment.setUser_id(latestUser.getUser_id());  // use id from DTO
	    return repo.save(payment);
	}



	
	@GetMapping("/pay")
	public List<Payment> getAllPayments() {
	    return repo.findAll();
	}

	
	@GetMapping("/pay/{paymentId}")
	public Optional<Payment> getPaymentById(@PathVariable Long paymentId) {
	    return repo.findById(paymentId);
	}
	
	
	@PutMapping("/pay/{paymentId}")
	public Payment updatePaymentStatus(@PathVariable Long paymentId, @RequestBody Payment updatedPayment) {
	    Payment payment = repo.findById(paymentId).orElseThrow();
	    payment.setPaymentStatus(updatedPayment.getPaymentStatus());
	    return repo.save(payment);
	}

	
	@GetMapping("/pay/status/{status}")
	public List<Payment> getPaymentsByStatus(@PathVariable Boolean status) {
	    return repo.findByPaymentStatus(status);
	}



}
