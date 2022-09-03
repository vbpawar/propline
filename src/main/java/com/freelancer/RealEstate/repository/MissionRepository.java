package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {

    @Query(value = "SELECT * FROM tab_mission WHERE isActive=true", nativeQuery = true)
    List<Mission> getActiveRecords();
}
