package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Integer> {

}
