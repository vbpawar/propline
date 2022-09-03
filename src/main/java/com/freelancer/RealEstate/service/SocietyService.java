package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Society;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * This is used to hold society data.
 *
 * @author Vikas Pawar
 */
@Service
public interface SocietyService {
    /**
     * @param society
     * @param street_id
     * @return
     */
    ResponseDto addSociety(Society society, Integer street_id);

    /**
     * @return
     * @throws SQLException
     */
    ResponseDto getSociety() throws SQLException;

    ResponseDto deleteSociety(Integer societyId);
}
