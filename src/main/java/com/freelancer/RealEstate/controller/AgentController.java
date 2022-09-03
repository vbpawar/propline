package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.model.EmailData;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.AgentService;
import com.freelancer.RealEstate.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private EmailService emailService;

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getAgents() {
        return agentService.getAgents();
    }

    /**
     * @param agents
     * @return
     */
    @PostMapping
    public ResponseDto addAgent(@RequestPart(value = PropertyConstant.AGENT) String agents,
                                @RequestPart(value = PropertyConstant.FILE_NAME,required = false) MultipartFile file) {
        return agentService.addAgent(agents,file);
    }

    @GetMapping("/getActiveAgents")
    public ResponseDto getActiveAgents() {
        return agentService.getActiveAgents();
    }

    @DeleteMapping
    public ResponseDto deleteAgent(@RequestParam(value = "agentId") Integer agentId) {
        return agentService.deleteAgents(agentId);
    }

    @PostMapping("/sendEmailToAgent")
    public ResponseDto sendEmailToAgent(@RequestBody EmailData emailData,@RequestHeader(value = "agentEmail") String agentEmail) {
        return emailService.sendDataToAgent(emailData,agentEmail);
    }
}
