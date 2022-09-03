package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.OurService;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface OurServices {
    ResponseDto getServices();

    ResponseDto addServices(OurService ourService);

    ResponseDto deleteService(Integer serviceId);
}
