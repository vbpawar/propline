package com.freelancer.RealEstate.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@Converter(autoApply = true)
public class ObjectConversion implements AttributeConverter<List<Object>, String> {

    Logger logger = LoggerFactory.getLogger(ObjectConversion.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Object> attribute) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    @Override
    public List<Object> convertToEntityAttribute(String dbData) {
        List<Object> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(dbData, List.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
