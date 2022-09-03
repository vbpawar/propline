package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Feedback;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public interface FeedbackService {
    /**
     * @return
     */
    ResponseDto getFeedbacks();

    /**
     * @param feedback
     * @return
     */
    ResponseDto addFeedback(Feedback feedback);

    ResponseDto deleteFeedback(Integer feedbackId);
}
