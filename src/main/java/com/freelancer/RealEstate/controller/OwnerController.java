package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseDto getOwners() {
        return ownerService.getOwners();
    }

    /**
     * @param ownerDetails
     * @param property_id
     * @param adharFile
     * @param panFile
     * @param profile
     * @return
     */
    @PostMapping
    public ResponseDto addOwner(@RequestPart(value = PropertyConstant.OWNER) String ownerDetails,
                                @RequestParam(value = PropertyConstant.PROPERTY_ID) Integer property_id,
                                @RequestPart(value = PropertyConstant.ADHAR, required = false) MultipartFile adharFile,
                                @RequestPart(value = PropertyConstant.PAN, required = false) MultipartFile panFile,
                                @RequestPart(value = PropertyConstant.PROFILE, required = false) MultipartFile profile,
                                @RequestPart(value = PropertyConstant.CO_ADHAR, required = false) MultipartFile co_adhar_card,
                                @RequestPart(value = PropertyConstant.CO_PAN, required = false) MultipartFile co_panFile,
                                @RequestPart(value = PropertyConstant.CO_PROFILE, required = false) MultipartFile co_profile) {
        return ownerService.addOwnerDetails(ownerDetails, property_id, adharFile, panFile, profile,co_adhar_card,co_profile,co_panFile);

    }

    /**
     * @param ownerId
     * @return
     */
    @DeleteMapping
    public ResponseDto deleteOwner(@RequestHeader(value = "ownerId") Integer ownerId) {
        return ownerService.deleteOwner(ownerId);
    }

    /**
     * @param propertyId
     * @return
     */
    @GetMapping("/getActivity")
    public ResponseDto getOwnerActivity(@RequestHeader(value = PropertyConstant.PROPERTY_ID) Integer propertyId) {
        return ownerService.getOwnerActivity(propertyId);
    }
}
