package com.demo.InsuranceClaim;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.springframework.stereotype.Component;


@WorkflowInterface
public interface ClaimProcessingWorkflow {

    @WorkflowMethod
    void processClaim(String claimId);
}
