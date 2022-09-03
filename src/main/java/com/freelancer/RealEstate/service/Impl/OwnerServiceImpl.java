package com.freelancer.RealEstate.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.config.ObjectConvert;
import com.freelancer.RealEstate.entity.Owner;
import com.freelancer.RealEstate.entity.OwnerActivity;
import com.freelancer.RealEstate.entity.Property;
import com.freelancer.RealEstate.model.OwnerDto;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.OwnerActivityRepo;
import com.freelancer.RealEstate.repository.OwnerRepository;
import com.freelancer.RealEstate.repository.PropertyRepository;
import com.freelancer.RealEstate.service.MediaService;
import com.freelancer.RealEstate.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private AwsConfig awsConfig;

    @Autowired
    private OwnerActivityRepo ownerActivityRepo;

    Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Override
    public ResponseDto getOwners() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Owner> ownerList = ownerRepository.findAll();
            responseDto.setData(ownerList);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setStatus(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }


    /**
     * @param owner1
     */
    private ResponseDto setActivity(Owner owner1) {
        ResponseDto responseDto = new ResponseDto();
        OwnerActivity ownerActivity = new OwnerActivity();
        try {
            ownerActivity.setOwner(owner1);
            ownerActivityRepo.save(ownerActivity);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    /**
     * @param adharFile
     * @param panFile
     * @param profile
     * @param owner1
     */
    private void uploadMediaForOwner(MultipartFile adharFile, MultipartFile panFile, MultipartFile profile, Owner owner1) {
        if (adharFile != null) {
            ResponseDto responseDto1 = mediaService.uploadMedia(adharFile);
            if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                owner1.setAdhar_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                owner1.setAdhar_title(prepareFileName(adharFile.getOriginalFilename()));
            }
        }

        if (profile != null) {
            ResponseDto responseDto1 = mediaService.uploadMedia(profile);
            if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                owner1.setProfile_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                owner1.setProfile(prepareFileName(adharFile.getOriginalFilename()));
            }
        }

        if (panFile != null) {
            ResponseDto responseDto1 = mediaService.uploadMedia(panFile);
            if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                owner1.setPan_card_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                owner1.setPan_card(prepareFileName(panFile.getOriginalFilename()));
            }
        }
    }

    /**
     * @param co_owner_details
     */
    private List<OwnerDto> setCoOwnerDetails(List<OwnerDto> co_owner_details) {
        List<OwnerDto> ownerDto1 = new ArrayList<>();
        try {
            co_owner_details.stream().forEach(
                    ownerDto -> {
                        OwnerDto ownerDto2 = new OwnerDto();
                        ownerDto2.setOwner_name(ownerDto.getOwner_name());
                        ownerDto2.setEmail(ownerDto.getEmail());
                        ownerDto2.setContact_number(ownerDto.getContact_number());
                        ownerDto2.setDob(ownerDto.getDob());
                        ownerDto2.setPermanent_address(ownerDto.getPermanent_address());
                        ownerDto1.add(ownerDto);
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ownerDto1;
    }

    /**
     * @param adharFile
     * @param panFile
     * @param profilePhoto
     * @param ownerDto1
     */
    private void uploadMediaForOwnerDto(MultipartFile adharFile, MultipartFile panFile, MultipartFile profilePhoto, OwnerDto ownerDto1) {
        if (adharFile != null) {
            ResponseDto responseDto1 = mediaService.uploadMedia(adharFile);
            if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                ownerDto1.setAdhar_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                ownerDto1.setAdhar_title(prepareFileName(adharFile.getOriginalFilename()));
            }
        }

        if (profilePhoto != null) {
            ResponseDto responseDto1 = mediaService.uploadMedia(profilePhoto);
            if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                ownerDto1.setProfile_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                ownerDto1.setProfile(prepareFileName(profilePhoto.getOriginalFilename()));
            }
        }

        if (panFile != null) {
            ResponseDto responseDto1 = mediaService.uploadMedia(panFile);
            if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                ownerDto1.setPan_card_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                ownerDto1.setPan_card(prepareFileName(panFile.getOriginalFilename()));
            }
        }
    }

    @Override
    public ResponseDto deleteOwner(Integer ownerId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Owner> owner = ownerRepository.findById(ownerId);
            if (owner.isPresent()) {
                ownerRepository.deleteById(ownerId);
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

    /**
     * @param property_id
     * @return
     */
    @Override
    public ResponseDto getOwnerActivity(Integer property_id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<List<OwnerActivity>> ownerList = ownerActivityRepo.getActivityRecordsFor(property_id);
            if (ownerList.isPresent()) {
                responseDto.setData(ownerList.get());
                responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
            } else {
                responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto.setStatusCode(PropertyConstant.NOT_FOUND);
            }
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatus(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param ownerDetails
     * @param property_id
     * @param adharFile
     * @param panFile
     * @param profile
     * @param co_adhar_card
     * @param co_profile
     * @param co_panFile
     * @return
     */
    @Override
    public ResponseDto addOwnerDetails(String ownerDetails, Integer property_id, MultipartFile adharFile, MultipartFile panFile, MultipartFile profile,
                                       MultipartFile co_adhar_card, MultipartFile co_profile, MultipartFile co_panFile) {
        ResponseDto responseDto = new ResponseDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Owner owner = objectMapper.readValue(ownerDetails, Owner.class);
            Owner owner1 = new Owner();

            if (owner.getOwner_id() != null) {
                Optional<Owner> owner2 = ownerRepository.findById(owner.getOwner_id());
                if (owner2.isPresent()) {
                    owner1 = owner2.get();
                }
            }
            Optional<Property> property = propertyRepository.findById(property_id);
            if (property.isPresent()) {
                owner1.setProperty(property.get());
            } else {
                owner1.setProperty(null);
            }

            uploadMediaForOwner(adharFile, panFile, profile, owner1);

            owner1.setOwner_name(owner.getOwner_name());
            owner1.setEmail(owner.getEmail());
            owner1.setContact_number(owner.getContact_number());
            owner1.setDob(owner.getDob());
            owner1.setPermanent_address(owner.getPermanent_address());
            try {
                logger.info("Coowner details:{}", owner.getOwnerDto());
                List<OwnerDto> ownerDto = setCoOwnerDetails(owner.getOwnerDto());
                uploadMediaForOwnerDto(co_adhar_card, co_panFile, co_profile, ownerDto.get(0));
                owner1.setOwnerDto(ownerDto);
            } catch (Exception e) {
                responseDto.setMessage(e.getMessage());
                responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            }
            owner1 = ownerRepository.save(owner1);
            ResponseDto responseDto1 = setActivity(owner1);
            if (responseDto1.getStatusCode() != PropertyConstant.SUCCESS_STATUS_CODE) {
                responseDto.setMessage(responseDto1.getMessage());
            } else {
                responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            }
            responseDto.setData(owner1);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param originalFilename
     * @return
     */
    private String prepareFileName(String originalFilename) {
        Long date = new Date().getTime();
        return date + StringUtils.cleanPath(originalFilename);
    }
}
