package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface TenantService {
    ResponseDto getTenants();

    /**
     *
     * @param tenant
     * @param ownerId
     * @param adharcard
     * @param pancard
     * @param profile
     * @return
     */
    ResponseDto addTenant(String tenant, Integer ownerId, MultipartFile adharcard, MultipartFile pancard, MultipartFile profile);

    ResponseDto deleteTenant(Integer tenantId);
}
