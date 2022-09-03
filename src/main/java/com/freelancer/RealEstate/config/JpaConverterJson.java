package com.freelancer.RealEstate.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

@Converter(autoApply = true)
public class JpaConverterJson implements AttributeConverter<Map<String, Object>, String> {

    Logger logger = LoggerFactory.getLogger(JpaConverterJson.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        Map<String, Object> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(dbData, Map.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return customerInfo;
    }
}
