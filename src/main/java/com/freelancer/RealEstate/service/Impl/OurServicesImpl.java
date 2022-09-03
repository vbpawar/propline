package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Neighborhood;
import com.freelancer.RealEstate.entity.OurService;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.ServicesRepository;
import com.freelancer.RealEstate.service.OurServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class OurServicesImpl implements OurServices {

    @Autowired
    private ServicesRepository servicesRepository;

    /**
     * @return
     */
    @Override
    public ResponseDto getServices() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.FAILED);
        Optional<List<OurService>> ourServiceList = Optional.of(servicesRepository.findAll());
        if (ourServiceList.isPresent()) {
            responseDto.setData(ourServiceList.get());
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        }
        return responseDto;
    }

    /**
     * @param ourService
     * @return
     */
    @Override
    public ResponseDto addServices(OurService ourService) {
        ResponseDto responseDto = new ResponseDto();
        OurService ourService1 = servicesRepository.save(ourService);
        responseDto.setData(ourService1);
        responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    @Override
    public ResponseDto deleteService(Integer serviceId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<OurService> ourService = servicesRepository.findById(serviceId);
            if (ourService.isPresent()) {
                servicesRepository.deleteById(serviceId);
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
