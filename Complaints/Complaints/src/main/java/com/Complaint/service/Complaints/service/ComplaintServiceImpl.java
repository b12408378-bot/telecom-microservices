package com.Complaint.service.Complaints.service;

import com.Complaint.service.Complaints.entity.Complaint;
import com.Complaint.service.Complaints.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repository;

    public ComplaintServiceImpl(ComplaintRepository repository) {
        this.repository = repository;
    }

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return repository.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
