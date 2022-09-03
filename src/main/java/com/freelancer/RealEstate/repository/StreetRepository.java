package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.AdditionalFeatures;
import com.freelancer.RealEstate.entity.Streets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Handle db detials.
 *
 * @author Vikas Pawar
 */
@Repository
public interface StreetRepository extends JpaRepository<Streets, Integer> {
}
