package com.demo.InsuranceClaim.services;

import com.demo.InsuranceClaim.workflows.ClaimProcessingWorkflow;
import com.demo.InsuranceClaim.dto.ClaimRequest;
import com.demo.InsuranceClaim.models.Claim;
import com.demo.InsuranceClaim.respositories.ClaimRepository;
import io.temporal.api.common.v1.WorkflowExecution;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private WorkflowClient workflowClient;


    public Claim saveClaim(ClaimRequest claimRequest) {
        Claim claim = new Claim();
        claim.setPolicyId(claimRequest.getPolicyId());
        claim.setClaimantName(claimRequest.getClaimantName());
        claim.setClaimAmount(claimRequest.getClaimAmount());
        claim.setClaimDate(claimRequest.getClaimDate());
        claim.setIncidentDetails(claimRequest.getIncidentDetails());
        claim.setClaimStatus("Pending");

        return claimRepository.save(claim);
    }

    public Claim getClaimById(String claimId) {
        return claimRepository.findById(claimId).orElseThrow(() -> new IllegalArgumentException("Claim not found"));
    }

    public void startClaimWorkflow(Claim claim) {


        // Create workflow options
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("ClaimTaskQueue")
                .setWorkflowId("insuranceClaim-processing-workflow")
                .build();

        ClaimProcessingWorkflow workflow = workflowClient.newWorkflowStub(ClaimProcessingWorkflow.class, options);

        // Perform asynchronous execution.
        // This process exits after making this call and printing details.
        WorkflowExecution we = WorkflowClient.start(workflow::processClaim, claim);
    }
}
