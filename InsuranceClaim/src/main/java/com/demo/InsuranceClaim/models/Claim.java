package com.demo.InsuranceClaim.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Document(collection = "claims")
public class Claim {
    @Id
    private String id;

    private String policyId;
    private String claimantName;
    private double claimAmount;
    private String claimStatus;
    private String incidentDetails;
    private String claimDate;

    // Getters and Setters

    public Claim(String id, String policyId, String claimantName, double claimAmount, String claimStatus, String incidentDetails, String claimDate) {
        this.id = id;
        this.policyId = policyId;
        this.claimantName = claimantName;
        this.claimAmount = claimAmount;
        this.claimStatus = claimStatus;
        this.incidentDetails = incidentDetails;
        this.claimDate = claimDate;
    }

    public Claim() {

    }

}
