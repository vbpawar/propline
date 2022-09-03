package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.PropertyType;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.PropertyTypeService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This class is used to handle property types.
 *
 * @author Vikas Pawar
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/propertyType", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class PropertyTypeController {

    @Autowired
    private PropertyTypeService propertyTypeService;


    /**
     * @param propertyType
     * @return
     */
    @PostMapping
    public ResponseDto addPropertyType(@Validated @NotNull @RequestBody PropertyType propertyType) {
        return propertyTypeService.addPropertyType(propertyType);
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getAllProperties() {
        return propertyTypeService.fetchAllProperties();
    }

    /**
     * @param propertyType
     * @return
     */
    @PutMapping
    public ResponseDto updatePropertyType(@Validated @RequestBody PropertyType propertyType) {
        return propertyTypeService.updatePropertyType(propertyType);
    }

    @DeleteMapping
    public ResponseDto deletePropertyType(@RequestHeader(value = PropertyConstant.PROPERTY_TYPE_ID) Integer property_id) {
        return propertyTypeService.deletePropertyType(property_id);
    }

}
