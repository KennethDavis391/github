package com.reverse.userservice.controllers;

import com.reverse.userservice.models.BranchLocation;
import com.reverse.userservice.models.Gender;
import com.reverse.userservice.services.InHouseVariablesService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "lists")
@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class GetInHouseVariablesController {

    @Autowired
    @Setter
    private InHouseVariablesService service;

    @GetMapping(value = "/genders")
    public ResponseEntity<List<Gender>> getAllGenders(){
        log.debug("Recovering all gender values");
        List<Gender> genderList = this.service.getAllGenders();
        log.debug("Returning all gender values");
        return ResponseEntity.ok().body(genderList);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<List<BranchLocation>> getAllLocations(){
        log.debug("Recovering all branch locations");
        List<BranchLocation> locations = this.service.getAllLocations();
        log.debug("Returning all branch locations");
        return ResponseEntity.ok().body(locations);
    }

}
