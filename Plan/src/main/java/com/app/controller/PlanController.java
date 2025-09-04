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
import com.app.model.Plan;
import com.app.repository.PlanRepository;



@RestController
@RequestMapping("/planservice")
public class PlanController {

	

	@Autowired
	public PlanRepository repo;
	

	@GetMapping("/plan")
	public List<Plan> displayPlans(){
		return repo.findAll();
	}

	

	@GetMapping("/plan/{planId}")
	public Optional<Plan> getUserById(@PathVariable int planId){
		return repo.findById(planId);
	}

	

	@GetMapping("/plan/price/{price}")
	public List<Plan> getPlansByPrice(@PathVariable double price) {
	    return repo.findByPrice(price);
	}
	

	@PostMapping
	public Plan createPlan(@RequestBody Plan plan) {
		return repo.save(plan);
	}

	
	@PutMapping("/plan/{planId}")
	public Plan updatePlan(@PathVariable int planId, @RequestBody Plan updatedPlan) {
	    Plan plan = repo.findById(planId).orElseThrow(); 
	    plan.setPlanName(updatedPlan.getPlanName());
	    plan.setPlanType(updatedPlan.getPlanType());
	    plan.setValidity(updatedPlan.getValidity());
	    plan.setPrice(updatedPlan.getPrice());
	    return repo.save(plan);

	}

}

