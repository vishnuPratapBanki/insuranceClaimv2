package com.demo.InsuranceClaim.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.springframework.stereotype.Component;

@Component
@ActivityInterface
public interface ClaimProcessingActivities {

    @ActivityMethod
    void submitClaim(String claimId);

    @ActivityMethod
    void verifyDocuments(String claimId);

    @ActivityMethod
    void evaluateClaim(String claimId);

    @ActivityMethod
    void settleClaim(String claimId);
}