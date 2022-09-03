package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Agents;
import com.freelancer.RealEstate.entity.Cities;
import com.freelancer.RealEstate.entity.Mission;
import com.freelancer.RealEstate.entity.Neighborhood;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.CitiesRepository;
import com.freelancer.RealEstate.repository.NeighborhoodRepository;
import com.freelancer.RealEstate.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    /**
     * @param neighborhood
     * @param city_id
     * @return
     */
    @Override
    public ResponseDto addNeighborhood(Neighborhood neighborhood, Integer city_id) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        Neighborhood neighborhood1 = new Neighborhood();
        Optional<Cities> cities = Optional.ofNullable(citiesRepository.findById(city_id).orElseThrow(() -> new Exception("City Not found")));
        neighborhood1.setNeighborhood(neighborhood.getNeighborhood());
        neighborhood1.setCities(cities.get());
        neighborhood1 = neighborhoodRepository.save(neighborhood1);
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        responseDto.setData(neighborhood1);
        return responseDto;
    }

    /**
     * @return
     */
    @Override
    public ResponseDto getNeighborhood() {
        ResponseDto responseDto = new ResponseDto();
        List<Neighborhood> neighborhoodList = neighborhoodRepository.findAll();
        responseDto.setData(neighborhoodList);
        return responseDto;
    }

    /**
     * @param neighborhood
     * @param city_id
     * @return
     */
    @Override
    public ResponseDto updateNeighborhood(Neighborhood neighborhood, Integer city_id) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        Neighborhood neighborhood1 = new Neighborhood();
        Optional<Cities> cities = Optional.ofNullable(citiesRepository.findById(city_id).orElseThrow(() -> new Exception("City Not found")));
        neighborhood1.setNeighborhood_id(neighborhood.getNeighborhood_id());
        neighborhood1.setNeighborhood(neighborhood.getNeighborhood());
        neighborhood1.setCities(cities.get());
        neighborhood1 = neighborhoodRepository.save(neighborhood);
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        responseDto.setData(neighborhood1);
        return responseDto;
    }

    @Override
    public ResponseDto getActiveNeighborHood() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Neighborhood> neighborhoodList = neighborhoodRepository.getActiveRecords();
            responseDto.setData(neighborhoodList);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteNeihbor(Integer neighborId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Neighborhood> neighborhood = neighborhoodRepository.findById(neighborId);
            if (neighborhood.isPresent()) {
                neighborhoodRepository.deleteById(neighborId);
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
