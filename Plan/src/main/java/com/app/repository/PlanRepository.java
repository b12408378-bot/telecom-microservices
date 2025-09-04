package com.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{
	List<Plan> findByPrice(double price);
}