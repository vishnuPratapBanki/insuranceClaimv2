package com.demo.InsuranceClaim.controllers;

import com.demo.InsuranceClaim.dto.ClaimRequest;
import com.demo.InsuranceClaim.models.Claim;
import com.demo.InsuranceClaim.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping
    public String submitClaim(@RequestBody ClaimRequest claimRequest) {
        // Save claim to MongoDB
        Claim claim = claimService.saveClaim(claimRequest);

        // Trigger the Temporal workflow to process the claim
        claimService.startClaimWorkflow(claim);

        return "Claim submitted successfully. Claim ID: " + claim.getId() + "policyID"+ claim.getPolicyId();
    }

    @GetMapping("/{id}")
    public Claim getClaim(@PathVariable String id) {
        return claimService.getClaimById(id);
    }
}
