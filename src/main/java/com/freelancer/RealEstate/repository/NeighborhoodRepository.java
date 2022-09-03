package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Integer> {

    @Query(value = "SELECT * FROM tab_neighborhood WHERE isActive=true", nativeQuery = true)
    List<Neighborhood> getActiveRecords();
}
