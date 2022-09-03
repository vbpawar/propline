package com.freelancer.RealEstate.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.AwsConfig;
import com.freelancer.RealEstate.entity.Agents;
import com.freelancer.RealEstate.entity.Streets;
import com.freelancer.RealEstate.entity.Testimonial;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.TestimonialRepository;
import com.freelancer.RealEstate.service.MediaService;
import com.freelancer.RealEstate.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private AwsConfig awsConfig;

    /**
     * @return
     */
    @Override
    public ResponseDto getTestimonials() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        Optional<List<Testimonial>> testimonialList = Optional.of(testimonialRepository.findAll());
        if (testimonialList.isPresent()) {
            responseDto.setData(testimonialList.get());
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        }
        return responseDto;
    }

    /**
     * @param testimonial
     * @param file
     * @return
     */
    @Override
    public ResponseDto addTestimonial(String testimonial, MultipartFile file) {
        ResponseDto responseDto = new ResponseDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Testimonial testimonial1 = objectMapper.readValue(testimonial, Testimonial.class);

            Testimonial testimonial3 = new Testimonial();
            if (testimonial1.getId() != null) {
                if (testimonialRepository.findById(testimonial1.getId()).isPresent()) {
                    testimonial3 = testimonialRepository.findById(testimonial1.getId()).get();
                }
            }
            testimonial3.setDescription(testimonial1.getDescription());
            testimonial3.setDesignation(testimonial1.getDesignation());
            testimonial3.setTitle(testimonial1.getTitle());

            String filename = "";
            if (null != file) {
                ResponseDto responseDto1 = mediaService.uploadMedia(file);
                if (responseDto1.getStatusCode() == PropertyConstant.SUCCESS_STATUS_CODE) {
                    filename = responseDto1.getData().toString();
                }
                testimonial3.setProfile(awsConfig.getImageUrl() + filename);
            }

            Testimonial testimonial2 = testimonialRepository.save(testimonial3);
            responseDto.setData(testimonial2);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteTestimonial(Integer testimonialId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Testimonial> testimonial = testimonialRepository.findById(testimonialId);
            if (testimonial.isPresent()) {
                testimonialRepository.deleteById(testimonialId);
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
