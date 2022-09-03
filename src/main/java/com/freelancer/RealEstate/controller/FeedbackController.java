package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.entity.Feedback;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    /**
     * @return
     */
    @GetMapping
    public ResponseDto getFeedbacks() {
        return feedbackService.getFeedbacks();
    }

    /**
     * @param feedback
     * @return
     */
    @PostMapping
    public ResponseDto addFeedback(@Validated @RequestBody Feedback feedback) {
        return feedbackService.addFeedback(feedback);
    }

    @DeleteMapping
    public ResponseDto deleteFeedback(@RequestHeader(value = "feedbackId") Integer feedbackId) {
        return feedbackService.deleteFeedback(feedbackId);
    }

}
