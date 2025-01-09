package com.demo.InsuranceClaim.workflows;

import com.demo.InsuranceClaim.models.Claim;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;


@WorkflowInterface
public interface ClaimProcessingWorkflow {

    @WorkflowMethod
    String processClaim(Claim claim);
}
