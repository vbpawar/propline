package com.freelancer.RealEstate.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.entity.Agents;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.AgentRepository;
import com.freelancer.RealEstate.service.AgentService;
import com.freelancer.RealEstate.service.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class AgentServiceImpl implements AgentService {

    Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private AwsConfig awsConfig;

    /**
     * @return
     */
    @Override
    public ResponseDto getAgents() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.FAILED);
        try {
            List<Agents> agentsList = agentRepository.findAll();
            responseDto.setData(agentsList);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto addAgent(String agents, MultipartFile file) {
        ResponseDto responseDto = new ResponseDto();
        try {
            logger.info("In agent service impl");
            ObjectMapper objectMapper = new ObjectMapper();
            Agents agents1 = objectMapper.readValue(agents, Agents.class);
            Agents agents3 = new Agents();
            if (agents1.getAgent_id() != null) {
                if (agentRepository.findById(agents1.getAgent_id()).isPresent()) {
                    agents3 = agentRepository.findById(agents1.getAgent_id()).get();
                }
            }
            agents3.setOrganisation(agents1.getOrganisation());
            agents3.setEmail(agents1.getEmail());
            agents3.setAgent_name(agents1.getAgent_name());
            agents3.setContact(agents1.getContact());
            agents3.setAgent_desc(agents1.getAgent_desc());

            if (null != file) {
                ResponseDto responseDto1 = mediaService.uploadMedia(file);
                String filename = "";
                if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    filename = responseDto1.getData().toString();
                }
                agents3.setProfile(awsConfig.getImageUrl() + filename);
            }


            Agents agents2 = agentRepository.save(agents3);
            responseDto.setData(agents2);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @return
     */
    @Override
    public ResponseDto getActiveAgents() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.FAILED);
        try {
            List<Agents> agentsList = agentRepository.getActiveRecords();
            responseDto.setData(agentsList);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteAgents(Integer agentId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.FAILED);
        try {
            Optional<Agents> agentsList = agentRepository.findById(agentId);
            if (agentsList.isPresent()) {
                agentRepository.deleteById(agentId);
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
