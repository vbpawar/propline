package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Property;
import com.freelancer.RealEstate.entity.PropertyType;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.PropertyTypeRepository;
import com.freelancer.RealEstate.service.PropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This service is used for property type service
 *
 * @author Vikas Pawar
 */
@Service
public class PropertyTypeServiceImpl implements PropertyTypeService {

    @Autowired
    private PropertyTypeRepository propertyTypeRepository;


    /**
     * @param propertyType
     * @return
     */
    @Override
    public ResponseDto addPropertyType(PropertyType propertyType) {
        ResponseDto responseDto = new ResponseDto();
        try {
            PropertyType propertyType1 = propertyTypeRepository.save(propertyType);
            responseDto.setData(propertyType1);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }

        return responseDto;
    }

    /**
     * @return
     */
    @Override
    public ResponseDto fetchAllProperties() {
        ResponseDto responseDto = new ResponseDto();
        List<PropertyType> propertyTypes = propertyTypeRepository.findAll();
        responseDto.setData(propertyTypes);
        return responseDto;
    }

    /**
     * @param propertyType
     * @return
     */
    @Override
    public ResponseDto updatePropertyType(PropertyType propertyType) {
        ResponseDto responseDto = new ResponseDto();
        PropertyType propertyType1 = new PropertyType();
        propertyType1 = propertyTypeRepository.save(propertyType);
        responseDto.setData(propertyType1);
        responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    @Override
    public ResponseDto deletePropertyType(Integer property_id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<PropertyType> propertyType = propertyTypeRepository.findById(property_id);
            if (propertyType.isPresent()) {
                propertyTypeRepository.deleteById(property_id);
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
