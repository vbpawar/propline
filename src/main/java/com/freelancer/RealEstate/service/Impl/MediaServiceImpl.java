package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.entity.PropertyMedia;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.MediaRepository;
import com.freelancer.RealEstate.service.MediaService;
import com.freelancer.RealEstate.service.S3DocumentUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class MediaServiceImpl implements MediaService {
    Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private S3DocumentUploadService s3DocumentUploadService;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private AwsConfig awsConfig;

    /**
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseDto uploadMedia(MultipartFile multipartFile) {
        ResponseDto responseDto1;
        responseDto1 = new ResponseDto();
        try {
            logger.info("in the uploadMedia service:");
            String filename = prepareFileName(multipartFile.getOriginalFilename());
            ResponseDto responseDto = s3DocumentUploadService.uploadFile(multipartFile, filename);
            if (responseDto.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                responseDto1.setStatus(PropertyConstant.SUCCESS_MSG);
                responseDto1.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
                filename = responseDto.getData().toString();
                responseDto1.setData(filename);
            } else {
                responseDto1.setStatus(PropertyConstant.FAILED);
                responseDto1.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
            }
        } catch (Exception exception) {
            responseDto1.setStatus(exception.getMessage());
            responseDto1.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto1;
    }

    /**
     * @param multipartFileList
     * @param property_id
     * @param mediaType
     * @return
     * @throws IOException
     */
    @Override
    public ResponseDto uploadMultipleFiles(List<MultipartFile> multipartFileList, Integer property_id, String mediaType) throws IOException {
        ResponseDto responseDto1 = new ResponseDto();
        List<PropertyMedia> propertyMedia = new ArrayList<>();
        logger.info("In the final media upload");
        try {
            String filename = "";
            logger.info("In the try block of media upload");
            for (MultipartFile multipartFile : multipartFileList) {
                filename = prepareFileName(multipartFile.getOriginalFilename());
                logger.info("For loop for upload:{}", filename);
                ResponseDto responseDto = s3DocumentUploadService.uploadFile(multipartFile, filename);
                if (responseDto.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    filename = responseDto.getData().toString();
                    PropertyMedia propertyMedia1 = new PropertyMedia();
                    propertyMedia1.setProperty_id(property_id);
                    propertyMedia1.setFile_path(awsConfig.getImageUrl() + filename);
                    propertyMedia1.setDoc_type(mediaType);
                    propertyMedia1.setTitle(filename);
                    propertyMedia.add(propertyMedia1);
                }
            }
            if (propertyMedia.size() > 0) {
                mediaRepository.saveAll(propertyMedia);
                responseDto1.setData(propertyMedia);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return responseDto1;
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
