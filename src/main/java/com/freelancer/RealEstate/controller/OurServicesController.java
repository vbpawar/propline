package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.OurService;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.OurServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/services")
public class OurServicesController {

    @Autowired
    private OurServices ourServices;

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getServices() {
        return ourServices.getServices();
    }

    /**
     * @param ourService
     * @return
     */
    @PostMapping
    public ResponseDto addServices(@Validated @RequestBody OurService ourService) {
        return ourServices.addServices(ourService);
    }

    @DeleteMapping
    public ResponseDto deleteService(@RequestHeader(value = "serviceId") Integer serviceId) {
        return ourServices.deleteService(serviceId);
    }

}
