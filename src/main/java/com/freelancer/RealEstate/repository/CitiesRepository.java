package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This Class is used for handle all db data for cities .
 *
 * @author Vikas Pawar
 */
@Repository
public interface CitiesRepository extends JpaRepository<Cities, Integer> {

    @Query(value = "SELECT * FROM tab_cities WHERE city = :city", nativeQuery = true)
    Optional<Cities> findByCity(@Param("city") String city);

    @Query(value = "SELECT * FROM tab_cities WHERE isActive = true", nativeQuery = true)
    List<Cities> getActiveRecords();
}
