package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.PropertyMedia;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *
 */
@Service
public interface MediaService {
    /**
     * @param multipartFile
     * @return
     */
    ResponseDto uploadMedia(MultipartFile multipartFile);


    /**
     *
     * @param multipartFileList
     * @return
     * @throws IOException
     */
    public ResponseDto uploadMultipleFiles(List<MultipartFile> multipartFileList,Integer property_id,String media) throws IOException;

}
