package com.freelancer.RealEstate.service.Impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.S3DocumentUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
@Service
public class S3DocumentUploadServiceImpl implements S3DocumentUploadService {

    Logger logger = LoggerFactory.getLogger(S3DocumentUploadServiceImpl.class);

    @Autowired
    private AwsConfig awsConfig;

    private AmazonS3 s3client;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());
        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1).build();
    }

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @Override
    public ResponseDto uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
        String fileUrl = "";
        ResponseDto responseDto = new ResponseDto();
        logger.info("in upload method of s3:{}", fileName);
        try {
           // File file = convertMultiPartToFile(multipartFile);
            InputStream file = multipartFile.getInputStream();
            fileUrl = awsConfig.getEndpoint() + "/" + fileName;
            logger.info("file url s3:{}", fileUrl);
            uploadFileTos3bucket(fileUrl, file);
            responseDto.setData(fileUrl);
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (IOException e) {
            logger.info("file url in catch:{}", e.getMessage());
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param fileName
     * @param file
     */
    private void uploadFileTos3bucket(String fileName, InputStream file) {
        logger.info("in config of s3 bucket");
        s3client.putObject(new PutObjectRequest(awsConfig.getBucket(), fileName, file,new ObjectMetadata())
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        logger.info("in convert file option");
        try {
            File convFile = new File(multipartFile.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();
            return convFile;
        } catch (IOException e) {
            logger.error("In try block of file converter:{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
