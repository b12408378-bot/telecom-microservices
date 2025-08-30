package com.Complaint.service.Complaints.controller;

import com.Complaint.service.Complaints.entity.Complaint;
import com.Complaint.service.Complaints.service.ComplaintService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintService.saveComplaint(complaint);
    }

    @GetMapping
    @CircuitBreaker(name = "complaintService", fallbackMethod = "fallbackComplaints")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "complaintService", fallbackMethod = "fallbackComplaint")
    public Complaint getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    // Circuit Breaker fallback methods
    public List<Complaint> fallbackComplaints(Exception e) {
        return List.of();
    }

    public Complaint fallbackComplaint(Long id, Exception e) {
        Complaint complaint = new Complaint();
        complaint.setId(id);
        complaint.setTitle("N/A");
        complaint.setDescription("Service unavailable. Please try again later.");
        complaint.setStatus("UNKNOWN");
        return complaint;
    }
}
