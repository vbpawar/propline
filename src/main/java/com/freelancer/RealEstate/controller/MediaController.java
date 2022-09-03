package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/uploadImage")
    public ResponseDto uploadMedia(@RequestPart(PropertyConstant.FILE_NAME) List<MultipartFile> multipartFile,
                                   @RequestParam(PropertyConstant.PROPERTY_ID) Integer property_id) throws IOException {
        return mediaService.uploadMultipleFiles(multipartFile, property_id, PropertyConstant.MEDIA);
    }
}
