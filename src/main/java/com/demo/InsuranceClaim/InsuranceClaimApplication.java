package com.demo.InsuranceClaim;

import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demo.InsuranceClaim")
public class InsuranceClaimApplication {


	public static void main(String[] args) {
		SpringApplication.run(InsuranceClaimApplication.class, args);
	}
}
