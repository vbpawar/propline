package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.PropertyType;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PropertyTypeService {

    /**
     *
     * @param propertyType
     * @return
     */
    ResponseDto addPropertyType(PropertyType propertyType);

    /**
     *
     * @return
     */
    ResponseDto fetchAllProperties();

    /**
     *
     * @param propertyType
     * @return
     */
    ResponseDto updatePropertyType(PropertyType propertyType);

    ResponseDto deletePropertyType(Integer property_id);
}
