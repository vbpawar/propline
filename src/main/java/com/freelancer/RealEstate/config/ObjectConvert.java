package com.freelancer.RealEstate.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

public class ObjectConvert implements AttributeConverter<Object, String> {

    Logger logger = LoggerFactory.getLogger(ObjectConvert.class);
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct <code>dbData</code> type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be
     *               converted
     * @return the converted value to be stored in the entity
     * attribute
     */
    @Override
    public Object convertToEntityAttribute(String dbData) {
        Object customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(dbData, Object.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
