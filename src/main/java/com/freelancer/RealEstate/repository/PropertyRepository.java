package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * This Class is used for handle db details.
 *
 * @author Vikas Pawar
 */
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    /**
     * @param active
     * @return
     */
    @Query(value = "SELECT * FROM tab_property WHERE is_property_active=:active", nativeQuery = true)
    List<Property> getActiveRecord(@Param("active") boolean active);

    @Query(value = "SELECT balcony_list,bedroom_list FROM tab_property",nativeQuery = true)
    List<Object> getParticularColumns();
}
