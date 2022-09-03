package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Property;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * This Service is used to handle property info.
 *
 * @author Vikas Pawar
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * @param property
     * @return
     */
    @PostMapping
    public ResponseDto addProperty(@RequestPart(value = PropertyConstant.PROPERTY) String property,
                                   @RequestParam(value = PropertyConstant.PROJECT_ID) Integer project_id,
                                   @RequestPart(value = PropertyConstant.COVERED_IMAGE,required = false) MultipartFile coveredImage,
                                   @RequestPart(value = PropertyConstant.MEDIA_FILES, required = false) List<MultipartFile> media_files,
                                   @RequestPart(value = PropertyConstant.DOC_FILES, required = false) List<MultipartFile> doc_files,
                                   @RequestPart(value = PropertyConstant.OTHER_FILES, required = false) List<MultipartFile> other_files,
                                   @RequestPart(value = PropertyConstant.REG_DOC, required = false) List<MultipartFile> reg_docs,
                                   @RequestPart(value = PropertyConstant.BANK_DOCS, required = false) List<MultipartFile> bank_docs,
                                   @RequestPart(value = PropertyConstant.COMP_DOCS, required = false) List<MultipartFile> comp_docs,
                                   @RequestPart(value = PropertyConstant.POA, required = false) List<MultipartFile> poa_doc) throws Exception {

        Property property1 = propertyService.propertyConverter(property);
        return propertyService.addPropertyDetails(property1, project_id, media_files, doc_files, other_files,reg_docs,bank_docs,comp_docs,poa_doc,coveredImage);
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getProperties() throws Exception {
        return propertyService.getProperties();
    }

    /**
     * @param property_id
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @GetMapping("/byPropertyId")
    public ResponseDto getPropertyByPropertyId(@RequestParam(PropertyConstant.PROPERTY_ID) Integer property_id) throws SQLException, IOException {
        return propertyService.getPropertyById(property_id);
    }

    @DeleteMapping
    public ResponseDto deleteProperty(@RequestHeader(value = PropertyConstant.PROPERTY_ID) Integer property_id) {
        return propertyService.deleteProperty(property_id);
    }
}
