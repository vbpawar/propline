package com.freelancer.RealEstate.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.freelancer.RealEstate.service.Impl.S3DocumentUploadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/api/v2")
public class DocumentController {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);
        private static String bucketName = "preownedproperties.dev";
        //   private static String keyName        = "Pharmerz"+ UUID.randomUUID();

        @RequestMapping(value = "/upload", method = RequestMethod.POST)
        public URL uploadFileHandler(@RequestParam("name") String name,
                                     @RequestParam("file") MultipartFile file) throws IOException {

/******* Printing all the possible parameter from @RequestParam *************/

            System.out.println("*****************************");

            System.out.println("file.getOriginalFilename() " + file.getOriginalFilename());
            System.out.println("file.getContentType()" + file.getContentType());
            System.out.println("file.getInputStream() " + file.getInputStream());
            System.out.println("file.toString() " + file.toString());
            System.out.println("file.getSize() " + file.getSize());
            System.out.println("name " + name);
            System.out.println("file.getBytes() " + file.getBytes());
            System.out.println("file.hashCode() " + file.hashCode());
            System.out.println("file.getClass() " + file.getClass());
            System.out.println("file.isEmpty() " + file.isEmpty());

            /*************Parameters to b pass to s3 bucket put Object **************/
            InputStream is = file.getInputStream();
            String keyName = file.getOriginalFilename();
    logger.info("In s3 file");

// Credentials for Aws
            AWSCredentials credentials = new BasicAWSCredentials("AKIA6GC7UF4GDGAWN6D5", "gUQzUD5e4r4aNMb0hFemUZ/l3haiACD9HnfauIEg");

            /****************** DocumentController.uploadfile(credentials); ***************************/


            AmazonS3 s3client = new AmazonS3Client(credentials);
            try {
                System.out.println("Uploading a new object to S3 from a file\n");
                logger.info("Uploading a new object to S3 from a file");
                //File file = new File(awsuploadfile);
                s3client.putObject(new PutObjectRequest(
                        bucketName, keyName, is, new ObjectMetadata()));


                URL url = s3client.generatePresignedUrl(bucketName, keyName, Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)));
                // URL url=s3client.generatePresignedUrl(bucketName,keyName, Date.from(Instant.now().plus(5, ChronoUnit.)));
                System.out.println("************************************");
                logger.info("URL:{}",url);
                System.out.println(url);

                return url;

            } catch (AmazonServiceException ase) {
                System.out.println("Caught an AmazonServiceException, which " +
                        "means your request made it " +
                        "to Amazon S3, but was rejected with an error response" +
                        " for some reason.");
                System.out.println("Error Message:{} " + ase.getMessage());
                logger.error("Error Message:{}",ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                logger.error("HTTP Status Code:{}",ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                logger.error("AWS Error Code:{}",ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
            } catch (AmazonClientException ace) {
                System.out.println("Caught an AmazonClientException, which " +
                        "means the client encountered " +
                        "an internal error while trying to " +
                        "communicate with S3, " +
                        "such as not being able to access the network.");
                System.out.println("Error Message: " + ace.getMessage());
                logger.error("catch block:{}",ace.getMessage());
            }

            return null;

        }

}
