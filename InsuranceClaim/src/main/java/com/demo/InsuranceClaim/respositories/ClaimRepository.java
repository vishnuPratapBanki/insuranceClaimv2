package com.demo.InsuranceClaim.respositories;

import com.demo.InsuranceClaim.models.Claim;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends MongoRepository<Claim, String> {

    Optional<Claim> findById(String id);

}
