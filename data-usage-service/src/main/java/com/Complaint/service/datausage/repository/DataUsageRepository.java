package com.Complaint.service.datausage.repository;

import com.Complaint.service.datausage.model.DataUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataUsageRepository extends JpaRepository<DataUsage, Long> {
    // Optional: custom queries if needed
}
