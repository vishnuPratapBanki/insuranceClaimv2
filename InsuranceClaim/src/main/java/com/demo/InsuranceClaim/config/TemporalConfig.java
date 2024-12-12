package com.demo.InsuranceClaim.config;

import com.demo.InsuranceClaim.ClaimProcessingActivities;
import com.demo.InsuranceClaim.ClaimProcessingWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newLocalServiceStubs();
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs serviceStubs) {
        return WorkflowClient.newInstance(serviceStubs);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public Worker claimWorker(WorkerFactory factory, ClaimProcessingActivities activities) {
        Worker worker = factory.newWorker("ClaimTaskQueue");
        worker.registerWorkflowImplementationTypes(ClaimProcessingWorkflowImpl.class);
        worker.registerActivitiesImplementations(activities); // Use Spring-managed bean
        return worker;
    }
}
