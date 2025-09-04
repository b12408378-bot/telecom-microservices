package com.Complaint.service.datausage.service;

import com.Complaint.service.datausage.model.DataUsage;
import com.Complaint.service.datausage.repository.DataUsageRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataUsageServiceImpl implements DataUsageService {

    private final DataUsageRepository dataUsageRepository;
    private static final int MONTHLY_LIMIT_GB = 50;

    public DataUsageServiceImpl(DataUsageRepository dataUsageRepository) {
        this.dataUsageRepository = dataUsageRepository;
    }

    @Override
    public DataUsage generateUsage(String planType) {
        Random rand = new Random();

        int usedGB = rand.nextInt(MONTHLY_LIMIT_GB - 1) + 1;
        int percentageUsed = (int) Math.round(((double) usedGB / MONTHLY_LIMIT_GB) * 100);

        List<Integer> dailyUsage = new ArrayList<>();
        int remaining = usedGB;
        int days = 30;

        for (int i = 0; i < days; i++) {
            if (i == days - 1) {
                dailyUsage.add(remaining);
            } else {
                int daily = rand.nextInt(Math.min(remaining, 3) + 1);
                dailyUsage.add(daily);
                remaining -= daily;
                if (remaining <= 0) {
                    for (int j = i + 1; j < days; j++) dailyUsage.add(0);
                    break;
                }
            }
        }

        DataUsage dataUsage = new DataUsage(planType, usedGB, percentageUsed, dailyUsage);

        // Save to DB
        return dataUsageRepository.save(dataUsage);
    }
}
