package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.entity.Cities;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/cities")
@Validated
public class CitiesController {

    @Autowired
    private CitiesService citiesService;


    /**
     * @param cities
     * @return
     */
    @PostMapping
    public ResponseDto addCity(@Validated @RequestBody Cities cities) {
        return citiesService.addCity(cities);
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseDto getAllCities() {
        return citiesService.fetchAllCities();
    }

    @GetMapping("/getActiveCities")
    public ResponseDto getActiveCities() {
        return citiesService.getActiveCities();
    }

    /**
     * @param cities
     * @return
     */
    @PutMapping
    public ResponseDto updateCity(@Validated @RequestBody Cities cities) {
        return citiesService.updateCity(cities);
    }

    @DeleteMapping
    public ResponseDto deleteCity(@RequestParam(value = "cityId") Integer cityId) {
        return citiesService.deleteCity(cityId);
    }
}
