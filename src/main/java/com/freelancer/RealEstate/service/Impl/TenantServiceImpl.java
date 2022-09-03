package com.freelancer.RealEstate.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.entity.Owner;
import com.freelancer.RealEstate.entity.Tenant;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.OwnerRepository;
import com.freelancer.RealEstate.repository.TenantRepository;
import com.freelancer.RealEstate.service.MediaService;
import com.freelancer.RealEstate.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private AwsConfig awsConfig;

    /**
     * @return
     */
    @Override
    public ResponseDto getTenants() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Tenant> tenants = tenantRepository.findAll();
            responseDto.setData(tenants);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setStatus(PropertyConstant.FAILED);
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param tenant
     * @param ownerId
     * @param adharcard
     * @param pancard
     * @param profile
     * @return
     */
    @Override
    public ResponseDto addTenant(String tenant, Integer ownerId, MultipartFile adharcard, MultipartFile pancard, MultipartFile profile) {
        ResponseDto responseDto = new ResponseDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Tenant tenant1 = objectMapper.readValue(tenant, Tenant.class);
            Tenant tenant3 = new Tenant();

            if (tenant1.getTenant_id() != null) {
                Optional<Tenant> tenant2 = tenantRepository.findById(tenant1.getTenant_id());
                if (tenant2.isPresent()) {
                    tenant3 = tenant2.get();
                }
            }
            if (ownerId != null) {
                Optional<Owner> owner2 = ownerRepository.findById(ownerId);
                if (owner2.isPresent()) {
                    tenant3.setOwner(owner2.get());
                }
            }
            tenant3.setTenant_address(tenant1.getTenant_address());
            tenant3.setTenant_name(tenant1.getTenant_name());
            tenant3.setContact_number(tenant1.getContact_number());
            tenant3.setAggrement_start_date(tenant1.getAggrement_start_date());
            tenant3.setGetAggrement_end_date(tenant1.getGetAggrement_end_date());
            tenant3.setDob(tenant1.getDob());
            tenant3.setEmail(tenant1.getEmail());
            tenant3.setCreated_on(new Date());

            if (adharcard != null) {
                ResponseDto responseDto1 = mediaService.uploadMedia(adharcard);
                if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    tenant3.setAdhar_card_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                    tenant3.setAdhar_card(prepareFileName(adharcard.getOriginalFilename()));
                }
            }

            if (pancard != null) {
                ResponseDto responseDto1 = mediaService.uploadMedia(pancard);
                if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    tenant3.setPan_card_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                    tenant3.setPan_card(prepareFileName(pancard.getOriginalFilename()));
                }
            }

            if (profile != null) {
                ResponseDto responseDto1 = mediaService.uploadMedia(profile);
                if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    tenant3.setProfile_photo_path(awsConfig.getImageUrl() + responseDto1.getData().toString());
                    tenant3.setProfile_photo(prepareFileName(profile.getOriginalFilename()));
                }
            }
            tenant3 = tenantRepository.save(tenant3);
            responseDto.setData(tenant3);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    private String prepareFileName(String originalFilename) {
        Long date = new Date().getTime();
        return date + StringUtils.cleanPath(originalFilename);
    }

    @Override
    public ResponseDto deleteTenant(Integer tenantId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Tenant> tenant = tenantRepository.findById(tenantId);
            if (tenant.isPresent()) {
                tenantRepository.deleteById(tenantId);
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
