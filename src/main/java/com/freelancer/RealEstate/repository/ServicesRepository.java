package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.OurService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<OurService, Integer> {
}
