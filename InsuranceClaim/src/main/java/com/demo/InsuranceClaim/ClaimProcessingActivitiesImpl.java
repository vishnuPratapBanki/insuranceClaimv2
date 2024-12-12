package com.demo.InsuranceClaim;
import com.demo.InsuranceClaim.models.Claim;
import com.demo.InsuranceClaim.respositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClaimProcessingActivitiesImpl implements ClaimProcessingActivities {

    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public void submitClaim(String claimId) {
        // Fetch claim from MongoDB and submit logic
        Claim claim = claimRepository.findById(claimId).orElseThrow(() -> new IllegalArgumentException("Claim not found"));
        claim.setClaimStatus("Submitted");
        claimRepository.save(claim);
    }

    @Override
    public void verifyDocuments(String claimId) {
        // Fetch claim from MongoDB and validate documents
        Claim claim = claimRepository.findById(claimId).orElseThrow(() -> new IllegalArgumentException("Claim not found"));
        // Simulate document verification logic
        claim.setClaimStatus("Documents Verified");
        claimRepository.save(claim);
    }

    @Override
    public void evaluateClaim(String claimId) {
        // Fetch claim from MongoDB and evaluate
        Claim claim = claimRepository.findById(claimId).orElseThrow(() -> new IllegalArgumentException("Claim not found"));
        // Simulate claim evaluation logic
        claim.setClaimStatus("Evaluated");
        claimRepository.save(claim);
    }

    @Override
    public void settleClaim(String claimId) {
        // Fetch claim from MongoDB and settle claim
        Claim claim = claimRepository.findById(claimId).orElseThrow(() -> new IllegalArgumentException("Claim not found"));
        // Simulate claim settlement logic
        claim.setClaimStatus("Settled");
        claimRepository.save(claim);
    }
}