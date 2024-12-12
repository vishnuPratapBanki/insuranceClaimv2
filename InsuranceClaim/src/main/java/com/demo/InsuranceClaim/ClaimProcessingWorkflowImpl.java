package com.demo.InsuranceClaim;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.springframework.stereotype.Component;

import java.time.Duration;


public class ClaimProcessingWorkflowImpl implements ClaimProcessingWorkflow {

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1)) // Wait 1 second before first retry
            .setMaximumInterval(Duration.ofSeconds(20)) // Do not exceed 20 seconds between retries
            .setBackoffCoefficient(2) // Wait 1 second, then 2, then 4, etc
            .setMaximumAttempts(3) // Fail after 5000 attempts
            .build();

    // ActivityOptions specify the limits on how long an Activity can execute before
    // being interrupted by the Orchestration service
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            .setRetryOptions(retryoptions) // Apply the RetryOptions defined above
            .setStartToCloseTimeout(Duration.ofSeconds(2)) // Max execution time for single Activity
            .setScheduleToCloseTimeout(Duration.ofSeconds(5000)) // Entire duration from scheduling to completion including queue time
            .build();

    private final ClaimProcessingActivities activities =
            Workflow.newActivityStub(ClaimProcessingActivities.class,defaultActivityOptions);

    @Override
    public void processClaim(String claimId) {
        // Call each activity in order
        activities.submitClaim(claimId);
        activities.verifyDocuments(claimId);
        activities.evaluateClaim(claimId);
        activities.settleClaim(claimId);
    }
}
