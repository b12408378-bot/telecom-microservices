package com.Complaint.service.datausage.data_usage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.Complaint.service.datausage")
@EnableJpaRepositories(basePackages = "com.Complaint.service.datausage.repository")
@EntityScan(basePackages = "com.Complaint.service.datausage.model")  // Added to scan entities
public class DataUsageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataUsageServiceApplication.class, args);
    }
}
