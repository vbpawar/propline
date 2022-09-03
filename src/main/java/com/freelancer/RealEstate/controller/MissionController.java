package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Mission;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping(path = "/api/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseDto getMissions() {
        return missionService.getMissions();
    }

    /**
     *
     * @param mission
     * @return
     */
    @PostMapping
    public ResponseDto addMission(@RequestPart(value = PropertyConstant.MISSION) String mission,
                                  @RequestPart(value = PropertyConstant.FILE_NAME,required = false) MultipartFile file) {
        return missionService.addMission(mission,file);
    }

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/File")
    public ResponseDto addMissionTest(@RequestParam(value = PropertyConstant.FILE_NAME) MultipartFile file) {
        return missionService.addMissionTest(file);
    }

    @GetMapping("/getActiveMissions")
    public ResponseDto getActiveMissions() {
        return missionService.getActiveMissions();
    }

    @DeleteMapping
    public ResponseDto deleteMission(@RequestHeader(value = "missionId") Integer missionId) {
        return missionService.deleteMission(missionId);
    }
}
