package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/testimonial")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseDto getTestimonials() {
        return testimonialService.getTestimonials();
    }

    /**
     *
     * @param testimonial
     * @return
     */
    @PostMapping
    public ResponseDto addTestimonial(@RequestPart(value = PropertyConstant.TESTIMONIAL) String testimonial,
                                      @RequestPart(value = PropertyConstant.FILE_NAME,required = false) MultipartFile file) {
        return testimonialService.addTestimonial(testimonial,file);
    }

    @DeleteMapping
    public ResponseDto deleteTestimonial(@RequestHeader(value = "testimonialId") Integer testimonialId) {
        return testimonialService.deleteTestimonial(testimonialId);
    }
}
