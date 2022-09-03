package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.AdditionalFeatures;
import com.freelancer.RealEstate.entity.Neighborhood;
import com.freelancer.RealEstate.entity.Society;
import com.freelancer.RealEstate.entity.Streets;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.FeatureRepository;
import com.freelancer.RealEstate.repository.NeighborhoodRepository;
import com.freelancer.RealEstate.repository.StreetRepository;
import com.freelancer.RealEstate.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetRepository streetRepository;

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Autowired
    private FeatureRepository featureRepository;

    /**
     * @param streets
     * @return
     */
    @Override
    public ResponseDto updateStreet(Streets streets, Integer neighborhood_id) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        Streets streets1 = new Streets();
        Optional<Neighborhood> neighborhood = Optional.ofNullable(neighborhoodRepository.findById(neighborhood_id).orElseThrow(() -> new Exception("Neighborhood Not found")));
        streets1.setStreet(streets.getStreet());
        streets1.setNeighborhood(neighborhood.get());
        streets1.setStreet_id(streets.getStreet_id());
        streets1 = streetRepository.save(streets);
        responseDto.setData(streets1);
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    /**
     * @param streets
     * @param neighborhood_id
     * @return
     */
    @Override
    public ResponseDto addStreet(Streets streets, Integer neighborhood_id) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        Streets streets1 = new Streets();
        Optional<Neighborhood> neighborhood = Optional.ofNullable(neighborhoodRepository.findById(neighborhood_id).orElseThrow(() -> new Exception("Neighborhood Not found")));
        streets1.setStreet(streets.getStreet());
        streets1.setNeighborhood(neighborhood.get());
        Streets streets2 = streetRepository.save(streets1);
        responseDto.setData(streets2);
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    /**
     * @return
     */
    @Override
    public ResponseDto getStreets() {
        ResponseDto responseDto = new ResponseDto();
        List<Streets> streetsList = streetRepository.findAll();
        responseDto.setData(streetsList);
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    /**
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public ResponseDto getFeatures() throws SQLException, IOException {
        ResponseDto responseDto = new ResponseDto();
        List<AdditionalFeatures> featuresList = featureRepository.findAll();
        responseDto.setData(featuresList);
        return responseDto;
    }

    /**
     * @param street_id
     * @return
     * @throws SQLException
     */
    @Override
    public Optional<Streets> getStreetDetails(Integer street_id) {
        Optional<Streets> streets = streetRepository.findById(street_id);
        return streets;
    }

    @Override
    public ResponseDto deleteStreet(Integer streetId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Streets> streets = streetRepository.findById(streetId);
            if (streets.isPresent()) {
                streetRepository.deleteById(streetId);
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
