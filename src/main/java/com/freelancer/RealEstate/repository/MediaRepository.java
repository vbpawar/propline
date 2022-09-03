package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.PropertyMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<PropertyMedia, Integer> {
}
