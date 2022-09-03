package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.OwnerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerActivityRepo extends JpaRepository<OwnerActivity, Integer> {

    @Query(value = "SELECT oa.* FROM tab_owner_activity oa LEFT JOIN tab_owner o ON oa.owner_id = o.owner_id LEFT JOIN tab_property tp ON tp.property_id = o.property_id WHERE tp.property_id=:property_id",nativeQuery = true)
    Optional<List<OwnerActivity>> getActivityRecordsFor(Integer property_id);
}

