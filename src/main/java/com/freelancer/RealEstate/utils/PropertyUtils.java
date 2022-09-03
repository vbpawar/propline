package com.freelancer.RealEstate.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.RealEstate.model.OwnerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class is used for all utils related info.
 *
 * @author Vikas Pawar
 */
@Component
public class PropertyUtils {

    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, Object> setOwnerDetails() {
        OwnerDetails ownerDetails = new OwnerDetails();
        return null;
    }
}
