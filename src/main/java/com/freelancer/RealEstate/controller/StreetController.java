package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Streets;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/street")
public class StreetController {

    @Autowired
    private StreetService streetService;

    /**
     * @param streets
     * @return
     */
    @PostMapping
    public ResponseDto addStreet(@Validated @RequestBody Streets streets,
                                 @RequestParam(value = PropertyConstant.NEIGHBORHOOD_ID) Integer neighborhood_id) throws Exception {
        return streetService.addStreet(streets, neighborhood_id);
    }

    /**
     * @param streets
     * @return
     */
    @PutMapping
    public ResponseDto updateStreet(@Validated @RequestBody Streets streets,
                                    @RequestParam(value = PropertyConstant.NEIGHBORHOOD_ID) Integer neighborhood_id) throws Exception {
        return streetService.updateStreet(streets, neighborhood_id);
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getStreets() {
        return streetService.getStreets();
    }


    /**
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @GetMapping("/getFeatures")
    public ResponseDto getFeatures() throws SQLException, IOException {
        return streetService.getFeatures();
    }

    @DeleteMapping
    public ResponseDto deleteStreet(@RequestHeader(PropertyConstant.STREET_ID) Integer streetId) {
        return streetService.deleteStreet(streetId);
    }

}
