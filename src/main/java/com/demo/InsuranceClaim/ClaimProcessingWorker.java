package com.demo.InsuranceClaim;

import com.demo.InsuranceClaim.activities.ClaimProcessingActivitiesImpl;
import com.demo.InsuranceClaim.workflows.ClaimProcessingWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ClaimProcessingWorker {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(InsuranceClaimApplication.class);

        // Get the Spring-managed ClaimProcessingActivitiesImpl bean
        ClaimProcessingActivitiesImpl activitiesImpl = context.getBean(ClaimProcessingActivitiesImpl.class);
        // Create a stub that accesses a Temporal Service on the local development machine
        WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newLocalServiceStubs();

        // The Worker uses the Client to communicate with the Temporal Service
        WorkflowClient client = WorkflowClient.newInstance(serviceStub);

        // A WorkerFactory creates Workers
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // A Worker listens to one Task Queue.
        // This Worker processes both Workflows and Activities
        Worker worker = factory.newWorker("ClaimTaskQueue");

        // Register a Workflow implementation with this Worker
        // The implementation must be known at runtime to dispatch Workflow tasks
        // Workflows are stateful so a type is needed to create instances.
        worker.registerWorkflowImplementationTypes(ClaimProcessingWorkflowImpl.class);

        // Register the Spring-managed Activity implementation
        worker.registerActivitiesImplementations(activitiesImpl);

        System.out.println("Worker is running and actively polling the Task Queue.");
        System.out.println("To quit, use ^C to interrupt.");

        // Start all registered Workers. The Workers will start polling the Task Queue.
        factory.start();
    }
}
