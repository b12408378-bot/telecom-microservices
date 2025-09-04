package com.Complaint.service.datausage.service;

import com.Complaint.service.datausage.model.DataUsage;

public interface DataUsageService {
    DataUsage generateUsage(String planType);
}
