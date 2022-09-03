package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 */
@Service
public interface S3DocumentUploadService {

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public ResponseDto uploadFile(MultipartFile multipartFile, String filename) throws IOException;

}
