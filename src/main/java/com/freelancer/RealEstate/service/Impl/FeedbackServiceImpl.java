package com.freelancer.RealEstate.service.Impl;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Feedback;
import com.freelancer.RealEstate.entity.Testimonial;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.repository.FeedbackRepository;
import com.freelancer.RealEstate.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    /**
     * @return
     */
    @Override
    public ResponseDto getFeedbacks() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(PropertyConstant.FAILED);
        Optional<List<Feedback>> feedbackList = Optional.of(feedbackRepository.findAll());
        if (feedbackList.isPresent()) {
            responseDto.setData(feedbackList.get());
            responseDto.setStatus(PropertyConstant.SUCCESS_MSG);
        }
        return responseDto;
    }

    /**
     * @param feedback
     * @return
     */
    @Override
    public ResponseDto addFeedback(Feedback feedback) {
        ResponseDto responseDto = new ResponseDto();
        Feedback feedback1 = feedbackRepository.save(feedback);
        responseDto.setData(feedback1);
        responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
        return responseDto;
    }

    @Override
    public ResponseDto deleteFeedback(Integer feedbackId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
            if (feedback.isPresent()) {
                feedbackRepository.deleteById(feedbackId);
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
