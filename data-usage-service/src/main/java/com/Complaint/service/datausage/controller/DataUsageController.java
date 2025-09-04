package com.Complaint.service.datausage.controller;

import com.Complaint.service.datausage.model.DataUsage;
import com.Complaint.service.datausage.service.DataUsageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-usage")
public class DataUsageController {

    private final DataUsageService dataUsageService;

    public DataUsageController(DataUsageService dataUsageService) {
        this.dataUsageService = dataUsageService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> getDataUsage(@RequestBody PlanRequest request) {
        String planType = request.getPlanType();
        if (planType == null || planType.isBlank()) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Plan type cannot be empty."));
        }

        planType = planType.trim().toLowerCase();

        switch (planType) {
            case "data":
            case "combo":
                DataUsage usage = dataUsageService.generateUsage(planType);
                return ResponseEntity.ok(usage);

            case "voice":
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(new ErrorMessage("Sorry, your plan does not include data usage tracking."));

            default:
                return ResponseEntity.badRequest()
                        .body(new ErrorMessage("Invalid plan type. Allowed: data, combo, voice."));
        }
    }

    // Request DTO
    static class PlanRequest {
        private String planType;
        public String getPlanType() { return planType; }
        public void setPlanType(String planType) { this.planType = planType; }
    }

    // Error message DTO
    static class ErrorMessage {
        private String message;
        public ErrorMessage(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
