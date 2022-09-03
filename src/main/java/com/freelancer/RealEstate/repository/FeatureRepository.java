package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.AdditionalFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<AdditionalFeatures, Integer> {
}
