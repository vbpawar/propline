package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface OwnerService {
    

    ResponseDto getOwners();

    ResponseDto deleteOwner(Integer ownerId);

    ResponseDto getOwnerActivity(Integer property_id);

    ResponseDto addOwnerDetails(String ownerDetails, Integer property_id, MultipartFile adharFile, MultipartFile panFile, MultipartFile profile, MultipartFile co_adhar_card, MultipartFile co_profile, MultipartFile co_panFile);
}
