package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Cities;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public interface CitiesService {

    /**
     * @param cities
     * @return
     */
    ResponseDto addCity(Cities cities);

    /**
     * @return
     */
    ResponseDto fetchAllCities();

    /**
     * @param cities
     * @return
     */
    ResponseDto updateCity(Cities cities);

    ResponseDto getActiveCities();

    ResponseDto deleteCity(Integer cityId);
}

