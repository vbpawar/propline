package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Dao Class to persist property data in to DB.
 *
 * @author vikas pawar
 */
@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, Integer> {

    @Query(value = "SELECT * FROM tab_property_type WHERE property_name = :property",nativeQuery = true)
    Optional<PropertyType> findByPropertyName(@Param("property") String propertyName);

    List<PropertyType> findAll();
}
