package com.freelancer.RealEstate.service;

import com.freelancer.RealEstate.entity.Streets;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 */
@Service
public interface StreetService {

    /**
     * @param streets
     * @return
     */
    ResponseDto updateStreet(Streets streets, Integer neighborhood_id) throws Exception;

    /**
     * @param streets
     * @param neighborhood_id
     * @return
     */
    ResponseDto addStreet(Streets streets, Integer neighborhood_id) throws Exception;

    /**
     * @return
     */
    ResponseDto getStreets();

    /**
     * @return
     * @throws SQLException
     * @throws IOException
     */
    ResponseDto getFeatures() throws SQLException, IOException;

    /**
     * @param street_id
     * @return
     */
    Optional<Streets> getStreetDetails(Integer street_id);

    ResponseDto deleteStreet(Integer streetId);
}
