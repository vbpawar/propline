package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is used for tenant data.
 *
 * @Author Vikas Pawar.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getTenants() {
        return tenantService.getTenants();
    }

    /**
     * @param tenant
     * @param ownerId
     * @param adharcard
     * @param pancard
     * @param profile
     * @return
     */
    @PostMapping
    public ResponseDto addTenant(@RequestPart(PropertyConstant.TENANT) String tenant,
                                 @RequestHeader(value = PropertyConstant.OWNER_ID) Integer ownerId,
                                 @RequestPart(value = PropertyConstant.ADHAR, required = false) MultipartFile adharcard,
                                 @RequestPart(value = PropertyConstant.PAN, required = false) MultipartFile pancard,
                                 @RequestPart(value = PropertyConstant.PROFILE, required = false) MultipartFile profile) {
        return tenantService.addTenant(tenant, ownerId, adharcard, pancard, profile);
    }

    /**
     * @param tenantId
     * @return
     */
    @DeleteMapping
    public ResponseDto deleteTenant(@RequestHeader(value = "tenantId") Integer tenantId) {
        return tenantService.deleteTenant(tenantId);
    }
}
