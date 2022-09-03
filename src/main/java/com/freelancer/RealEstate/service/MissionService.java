package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Mission;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@Service
public interface MissionService {
    /**
     * @return
     */
    ResponseDto getMissions();


    /**
     * @param mission
     * @param file
     * @return
     */
    ResponseDto addMission(String mission, MultipartFile file);

    ResponseDto addMissionTest(MultipartFile file);

    ResponseDto getActiveMissions();

    ResponseDto deleteMission(Integer missionId);
}
