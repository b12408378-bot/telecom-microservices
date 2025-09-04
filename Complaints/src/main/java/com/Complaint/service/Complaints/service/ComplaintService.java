package com.Complaint.service.Complaints.service;

import com.Complaint.service.Complaints.entity.Complaint;
import java.util.List;

public interface ComplaintService {
    Complaint saveComplaint(Complaint complaint);
    List<Complaint> getAllComplaints();
    Complaint getComplaintById(Long id);
}
