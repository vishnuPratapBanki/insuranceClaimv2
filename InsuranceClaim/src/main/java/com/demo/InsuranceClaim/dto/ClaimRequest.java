package com.demo.InsuranceClaim.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClaimRequest {
    private String policyId;
    private String claimantName;
    private double claimAmount;
    private String claimDate;
    private String incidentDetails;

    // Getters and Setters

    public ClaimRequest(String policyId, String claimantName, double claimAmount, String claimDate, String incidentDetails) {
        this.policyId = policyId;
        this.claimantName = claimantName;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.incidentDetails = incidentDetails;
    }

}
