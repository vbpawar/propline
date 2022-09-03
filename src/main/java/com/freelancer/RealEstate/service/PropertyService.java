package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Property;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
@Service
public interface PropertyService {

    /**
     * @return
     */
    ResponseDto getProperties() throws Exception;

    /**
     * @param property_id
     * @return
     */
    ResponseDto getPropertyById(Integer property_id) throws SQLException, IOException;

    /**
     * @param property
     * @return
     */
    Property propertyConverter(String property);

    /**
     * @param property1
     * @param project_id
     * @param media_files
     * @param doc_files
     * @param other_files
     * @param societyRegCertificate
     * @param bankDocs
     * @param compDocs
     * @return
     * @throws IOException
     */
    ResponseDto addPropertyDetails(Property property1, Integer project_id, List<MultipartFile> media_files,
                                   List<MultipartFile> doc_files, List<MultipartFile> other_files,
                                   List<MultipartFile> societyRegCertificate,
                                   List<MultipartFile> bankDocs,
                                   List<MultipartFile> compDocs,
                                   List<MultipartFile> poa_docs,
                                   MultipartFile coveredImage) throws IOException;

    ResponseDto deleteProperty(Integer property_id);
}
