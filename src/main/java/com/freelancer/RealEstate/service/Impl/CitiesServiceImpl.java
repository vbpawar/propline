package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Agents;
import com.freelancer.RealEstate.entity.Cities;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.CitiesRepository;
import com.freelancer.RealEstate.service.CitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This Class is used for impl of cities.
 *
 * @author Vikas Pawar
 */
@Service
public class CitiesServiceImpl implements CitiesService {

    Logger logger = LoggerFactory.getLogger(CitiesServiceImpl.class);

    @Autowired
    private CitiesRepository citiesRepository;

    /**
     * @param cities
     * @return
     */
    @Override
    public ResponseDto addCity(Cities cities) {
        logger.info("In addCity service impl");
        Cities cities1 = new Cities();
        ResponseDto responseDto = new ResponseDto();
        try {
            cities1 = citiesRepository.save(cities);
            responseDto.setData(cities1);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }

        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    /**
     * @return
     */
    @Override
    public ResponseDto fetchAllCities() {
        logger.info("In fetchcities service impl");
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Cities> cities = citiesRepository.findAll();
            responseDto.setData(cities);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }

        return responseDto;
    }

    /**
     * @param cities
     * @return
     */
    @Override
    public ResponseDto updateCity(Cities cities) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        cities = citiesRepository.save(cities);
        responseDto.setData(cities);
        return responseDto;
    }

    @Override
    public ResponseDto getActiveCities() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Cities> cities = citiesRepository.getActiveRecords();
            responseDto.setData(cities);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteCity(Integer cityId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Cities> cities = citiesRepository.findById(cityId);
            if (cities.isPresent()) {
                citiesRepository.deleteById(cityId);
                responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
            } else {
                responseDto.setStatus(PropertyConstant.NO_DATA);
                responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            }
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }
}
