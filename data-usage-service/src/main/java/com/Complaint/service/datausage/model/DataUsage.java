package com.Complaint.service.datausage.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "data_usage")
public class DataUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String planType;

    private int monthlyLimitGB = 50;
    private int usedGB;
    private int percentageUsed;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "daily_usage", joinColumns = @JoinColumn(name = "data_usage_id"))
    @Column(name = "daily_gb")
    private List<Integer> dailyUsageGB;

    public DataUsage() {}

    public DataUsage(String planType, int usedGB, int percentageUsed, List<Integer> dailyUsageGB) {
        this.planType = planType;
        this.usedGB = usedGB;
        this.percentageUsed = percentageUsed;
        this.dailyUsageGB = dailyUsageGB;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }

    public int getMonthlyLimitGB() { return monthlyLimitGB; }
    public void setMonthlyLimitGB(int monthlyLimitGB) { this.monthlyLimitGB = monthlyLimitGB; }

    public int getUsedGB() { return usedGB; }
    public void setUsedGB(int usedGB) { this.usedGB = usedGB; }

    public int getPercentageUsed() { return percentageUsed; }
    public void setPercentageUsed(int percentageUsed) { this.percentageUsed = percentageUsed; }

    public List<Integer> getDailyUsageGB() { return dailyUsageGB; }
    public void setDailyUsageGB(List<Integer> dailyUsageGB) { this.dailyUsageGB = dailyUsageGB; }
}
