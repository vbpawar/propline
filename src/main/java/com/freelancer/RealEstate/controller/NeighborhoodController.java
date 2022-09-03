package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Neighborhood;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

/**
 * This Class is used for handle neighborhood controller.
 *
 * @author Vikas Pawar
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/neighborhood")
public class NeighborhoodController {

    @Autowired
    private NeighborhoodService neighborhoodService;

    /**
     * @param neighborhood
     * @return
     */
    @PostMapping
    public ResponseDto addNeighborhood(@Validated @RequestBody Neighborhood neighborhood,
                                       @RequestParam(value = PropertyConstant.CITY_ID) Integer city_id) throws Exception {
        return neighborhoodService.addNeighborhood(neighborhood, city_id);
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getNeighborhood() {
        return neighborhoodService.getNeighborhood();
    }

    /**
     * @param neighborhood
     * @return
     */
    @PutMapping
    public ResponseDto updateNeighborhood(@Validated @RequestBody Neighborhood neighborhood,
                                          @RequestParam(value = PropertyConstant.CITY_ID) Integer city_id) throws Exception {
        return neighborhoodService.updateNeighborhood(neighborhood, city_id);
    }

    @GetMapping("/getActiveNeighborHood")
    public ResponseDto getActiveMissions() {
        return neighborhoodService.getActiveNeighborHood();
    }


    @DeleteMapping
    public ResponseDto deleteNeighbor(@RequestHeader(PropertyConstant.NEIGHBORHOOD_ID) Integer neighborId) {
        return neighborhoodService.deleteNeihbor(neighborId);
    }
}
