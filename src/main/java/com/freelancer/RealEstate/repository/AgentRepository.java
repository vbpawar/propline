package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Agents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface AgentRepository extends JpaRepository<Agents, Integer> {


    @Query(value = "SELECT * FROM tab_agents WHERE isActive= true",nativeQuery = true)
    List<Agents> getActiveRecords();
}
