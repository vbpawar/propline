package com.freelancer.RealEstate.controller;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.entity.Society;
import com.freelancer.RealEstate.model.ResponseDto;
import com.freelancer.RealEstate.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/society")
public class SocietyController {

    @Autowired
    private SocietyService societyService;

    /**
     * @param society
     * @param street_id
     * @return
     * @throws SQLException
     */
    @PostMapping
    public ResponseDto addSociety(@Validated @RequestBody Society society,
                                  @RequestParam(PropertyConstant.STREET_ID) Integer street_id) throws SQLException {
        return societyService.addSociety(society, street_id);
    }

    /**
     * @return
     * @throws SQLException
     */
    @GetMapping
    public ResponseDto getSociety() throws SQLException {
        return societyService.getSociety();
    }

    @DeleteMapping
    public ResponseDto deleteSociety(@RequestHeader(value = "societyId") Integer societyId) {
        return societyService.deleteSociety(societyId);
    }
}

