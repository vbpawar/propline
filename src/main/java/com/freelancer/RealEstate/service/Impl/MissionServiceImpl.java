package com.freelancer.RealEstate.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.entity.Cities;
import com.freelancer.RealEstate.entity.Mission;
import com.freelancer.RealEstate.entity.Testimonial;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.MissionRepository;
import com.freelancer.RealEstate.service.MediaService;
import com.freelancer.RealEstate.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private AwsConfig awsConfig;

    /**
     * @return
     */
    @Override
    public ResponseDto getMissions() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        Optional<List<Mission>> missionList = Optional.of(missionRepository.findAll());
        if (missionList.isPresent()) {
            responseDto.setData(missionList.get());
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param mission
     * @param file
     * @return
     */
    @Override
    public ResponseDto addMission(String mission, MultipartFile file) {
        ResponseDto responseDto = new ResponseDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Mission mission1 = objectMapper.readValue(mission, Mission.class);

            Mission mission3 = new Mission();
            if (mission1.getId() != null) {
                if (missionRepository.findById(mission1.getId()).isPresent()) {
                    mission3 = missionRepository.findById(mission1.getId()).get();
                }
            }
            mission3.setMissionModels(mission1.getMissionModels());

            if (null != file) {
                ResponseDto responseDto1 = mediaService.uploadMedia(file);
                if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    mission3.setImage(awsConfig.getImageUrl() + responseDto1.getData().toString());

                }
            }
            Mission mission2 = missionRepository.save(mission3);
            responseDto.setData(mission2);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto addMissionTest(MultipartFile file) {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setMessage(file.getOriginalFilename());
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);

        } catch (Exception e) {
            responseDto.setMessage(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getActiveMissions() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Mission> missionList = missionRepository.getActiveRecords();
            responseDto.setData(missionList);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteMission(Integer missionId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Mission> mission = missionRepository.findById(missionId);
            if (mission.isPresent()) {
                missionRepository.deleteById(missionId);
                responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
            } else {
                responseDto.setStatus(PropertyConstant.NO_DATA);
                responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            }
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }
}
