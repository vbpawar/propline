package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@Service
public interface AgentService {
    ResponseDto getAgents();


    /**
     * @param agents
     * @param file
     * @return
     */
    ResponseDto addAgent(String agents, MultipartFile file);

    ResponseDto getActiveAgents();

    ResponseDto deleteAgents(Integer agentId);
}
