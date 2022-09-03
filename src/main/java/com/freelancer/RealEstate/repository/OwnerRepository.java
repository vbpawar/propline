package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Query(value = "SELECT * FROM tab_owner",nativeQuery = true)
    List<Owner> getOwners();
}
