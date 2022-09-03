package com.freelancer.RealEstate.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.entity.*;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.PropertyRepository;
import com.freelancer.RealEstate.repository.SocietyRepository;
import com.freelancer.RealEstate.service.MediaService;
import com.freelancer.RealEstate.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * This class is used for handle service impl.
 *
 * @author Vikas Pawar
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    private AwsConfig awsConfig;

    /**
     * @param multipartFileList
     * @param property1
     * @return
     * @throws IOException
     */
    private List<PropertyMedia> saveMediaFiles(List<MultipartFile> multipartFileList, Property property1, String media) throws Exception {
        logger.info("in the saveMediaFiles service:{}", media);
        List<PropertyMedia> propertyMedia = new ArrayList<>();
        try {
            ResponseDto responseDto1 = mediaService.uploadMultipleFiles(multipartFileList, property1.getPropertyId(), media);
            logger.info("Response DTO of media upload:{}", responseDto1);
            if (responseDto1 != null && responseDto1.getData() != null) {
                propertyMedia = (List<PropertyMedia>) responseDto1.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyMedia;
    }

    /**
     * @param property
     * @param property1
     */
    private void setPropertyDetails(Property property, Property property1) {

        property1.setProperty_desc(property.getProperty_desc());
        property1.setProperty_type(property.getProperty_type());
        property1.setProperty_features(property.getProperty_features());
        property1.setProperty_for(property.getProperty_for());
        property1.setProperty_area(property.getProperty_area());
        property1.setPrice_details(property.getPrice_details());
        property1.setStatus_of_water(property.getStatus_of_water());
        property1.setOwnership_status(property.getOwnership_status());
        property1.setTransaction_type(property.getTransaction_type());
        property1.setPossession_status(property.getPossession_status());
        property1.setAge_of_property(property.getAge_of_property());
        property1.setOwnerDetails(property.getOwnerDetails());
        property1.setBedroomsList(property.getBedroomsList());
        property1.setIs_feature_property(property.isIs_feature_property());
        property1.setIs_hot_listed(property.isIs_hot_listed());
        property1.setRent_lease_details(property.getRent_lease_details());
        property1.setExtra_details(property.getExtra_details());
        property1.setIs_property_active(true);
        property1.setBalcony_list(property.getBalcony_list());
        property1.setLeaving_room_details(property.getLeaving_room_details());
        property1.setLobby_details(property.getLobby_details());
        property1.setKitchen_details(property.getKitchen_details());
        property1.setAmenities(property.getAmenities());
        property1.setFlooring(property.getFlooring());
        property1.setOverlooking(property.getOverlooking());
        property1.setFurnished_items(property.getFurnished_items());
    }


    /**
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDto getProperties() throws Exception {
        ResponseDto responseDto;
        responseDto = new ResponseDto();
        try {
            List<Property> property = propertyRepository.getActiveRecord(PropertyConstant.ACTIVE);
            //List<Object> property = propertyRepository.getParticularColumns();
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setData(property);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setMessage(PropertyConstant.NO_DATA);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param property_id
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public ResponseDto getPropertyById(Integer property_id) throws SQLException, IOException {
        logger.info("In Get property service for property id:{}", property_id);
        ResponseDto responseDto;
        responseDto = new ResponseDto();
        try {
            Optional<Property> property = propertyRepository.findById(property_id);
            if (property.isPresent()) {
                responseDto.setData(property.get());
                responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto.setMessage("Property Data");
                responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
            } else {
                responseDto.setStatus(PropertyConstant.NO_DATA);
                responseDto.setMessage("Property Data Not found");
                responseDto.setStatusCode(PropertyConstant.BAD_REQUEST);
            }
            logger.info("Property Data:{}", property.get());
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            responseDto.setMessage(PropertyConstant.NO_DATA);
        }
        return responseDto;
    }

    /**
     * @param property
     * @return
     */
    @Override
    public Property propertyConverter(String property) {
        logger.info("In property converter service:{}", property);
        Property property1 = new Property();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            property1 = objectMapper.readValue(property, Property.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property1;
    }

    /**
     * @param property
     * @param project_id
     * @param media_files
     * @param doc_files
     * @param other_files
     * @return
     */
    @Transactional
    @Override
    public ResponseDto addPropertyDetails(Property property, Integer project_id,
                                          List<MultipartFile> media_files, List<MultipartFile> doc_files,
                                          List<MultipartFile> other_files,
                                          List<MultipartFile> societyRegCertificate,
                                          List<MultipartFile> bankDocs,
                                          List<MultipartFile> compDocs,
                                          List<MultipartFile> poa_docs,
                                          MultipartFile coveredImage) throws IOException {
        logger.info("In the Service Impl of save property details");
        ResponseDto responseDto = new ResponseDto();
        Property property1 = new Property();
        try {

            //for update a property set property id
            if (property.getPropertyId() != null) {
                if (propertyRepository.findById(property.getPropertyId()).isPresent()) {
                    logger.info("property id:{}", property.getPropertyId());
                    property1 = propertyRepository.findById(property.getPropertyId()).get();
                }
            }
            Optional<Society> society = Optional.ofNullable(societyRepository.findById(project_id).orElseThrow(() -> new Exception("Society not found - ")));
            if (society.isPresent()) {
                property1.setSociety(society.get());
                String propertyName = createPropertyName(society.get(), property.getExtra_details());
                property1.setProperty_name(propertyName);
            }
            setPropertyDetails(property, property1);
            property1 = propertyRepository.save(property1);
            logger.info("property details:{}", property1);

            if (coveredImage != null) {
                ResponseDto responseDto1 = mediaService.uploadMedia(coveredImage);
                if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    property1.setCovered_image(awsConfig.getImageUrl() + responseDto1.getData().toString());

                }
            }
            if (null != media_files) {
                logger.info("In the file method");
                List<PropertyMedia> propertyMedia = saveMediaFiles(media_files, property1, PropertyConstant.MEDIA);
                property1.setPropertyMedia(propertyMedia);
            }
            if (null != doc_files) {
                List<PropertyMedia> docList = saveMediaFiles(doc_files, property1, PropertyConstant.DOCS);
                property1.setDocumentList(docList);
            }
            if (null != other_files) {
                List<PropertyMedia> other_docs = saveMediaFiles(other_files, property1, PropertyConstant.OTHER);
                property1.setOtherDocs(other_docs);
            }
            if (null != societyRegCertificate) {
                List<PropertyMedia> reg_certificates = saveMediaFiles(societyRegCertificate, property1, PropertyConstant.REG_DOC);
                property1.setSocietyRegCertificate(reg_certificates);
            }
            if (null != bankDocs) {
                List<PropertyMedia> bank_docs = saveMediaFiles(bankDocs, property1, PropertyConstant.BANK_DOCS);
                property1.setBankDocs(bank_docs);
            }
            if (null != compDocs) {
                List<PropertyMedia> cdocs = saveMediaFiles(compDocs, property1, PropertyConstant.COMP_DOCS);
                property1.setCompletionDocs(cdocs);
            }
            if (null != poa_docs) {
                List<PropertyMedia> poa = saveMediaFiles(poa_docs, property1, PropertyConstant.POA);
                property1.setPoa_docs(poa);
            }

            logger.info("Data saved in property service");
            responseDto.setData(property1);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error occured in add service:{}", e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            responseDto.setMessage(e.getMessage());
        }

        return responseDto;
    }

    private String createPropertyName(Society society, Map<String, Object> extra_details) {
        String street = society.getStreets().getNeighborhood().getNeighborhood();
        String societyName = society.getSociety();
        societyName = societyName.replace(" ", "");
        String propertyName = street.substring(0, 3) + societyName;
        logger.info("extra Details:{}", extra_details);
        if (extra_details != null) {
            String flat_number = "";
            logger.info("extra Details:{}", extra_details);
            logger.info("Flat Number:{}", extra_details.get("flat_no"));
            flat_number = (String) extra_details.get("flat_no");
            propertyName = propertyName + flat_number;
        }

        propertyName = propertyName.replaceAll("\\s", "");
        propertyName = propertyName.toUpperCase();
        return propertyName;
    }


    @Override
    public ResponseDto deleteProperty(Integer property_id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Property> property = propertyRepository.findById(property_id);
            if (property.isPresent()) {
                propertyRepository.deleteById(property_id);
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
