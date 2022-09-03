package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Neighborhood;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public interface NeighborhoodService {

    /**
     * @param neighborhood
     * @param city_id
     * @return
     */
    ResponseDto addNeighborhood(Neighborhood neighborhood, Integer city_id) throws Exception;

    /**
     * @return
     */
    ResponseDto getNeighborhood();

    /**
     *
     * @param neighborhood
     * @param city_id
     * @return
     */
    ResponseDto updateNeighborhood(Neighborhood neighborhood, Integer city_id) throws Exception;

    ResponseDto getActiveNeighborHood();

    ResponseDto deleteNeihbor(Integer neighborId);
}
