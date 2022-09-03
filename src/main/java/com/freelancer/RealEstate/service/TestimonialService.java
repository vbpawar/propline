package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Testimonial;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@Service
public interface TestimonialService {
    /**
     * @return
     */
    ResponseDto getTestimonials();


    /**
     * @param testimonial
     * @param file
     * @return
     */
    ResponseDto addTestimonial(String testimonial, MultipartFile file);

    ResponseDto deleteTestimonial(Integer testimonialId);
}
