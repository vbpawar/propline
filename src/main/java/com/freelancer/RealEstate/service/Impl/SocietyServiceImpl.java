package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.PropertyType;
import com.freelancer.RealEstate.entity.Society;
import com.freelancer.RealEstate.entity.Streets;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.SocietyRepository;
import com.freelancer.RealEstate.repository.StreetRepository;
import com.freelancer.RealEstate.service.SocietyService;
import com.freelancer.RealEstate.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * This is used for society service impl.
 *
 * @Author Vikas Pawar
 */
@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    private StreetRepository streetRepository;

    @Autowired
    private StreetService streetService;


    /**
     * @param society
     * @param street_id
     * @return
     */
    @Override
    public ResponseDto addSociety(Society society, Integer street_id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Streets> streets = streetService.getStreetDetails(street_id);
            if (streets.isPresent()) {
                society.setStreets(streets.get());
            }
            Society society1 = societyRepository.save(society);
            responseDto.setData(society1);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    @Override
    public ResponseDto getSociety() throws SQLException {
        Optional<List<Society>> societyList = Optional.of(societyRepository.findAll());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(societyList);
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    @Override
    public ResponseDto deleteSociety(Integer societyId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Society> society = societyRepository.findById(societyId);
            if (society.isPresent()) {
                societyRepository.deleteById(societyId);
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
