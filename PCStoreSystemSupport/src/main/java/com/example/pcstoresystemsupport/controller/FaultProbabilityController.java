package com.example.pcstoresystemsupport.controller;

import com.example.pcstoresystemsupport.dtos.ComponentFailureDTO;
import com.example.pcstoresystemsupport.service.BayesService;
import com.example.pcstoresystemsupport.service.FuzzyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unbbayes.prs.exception.InvalidParentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/faultProbability", produces = MediaType.APPLICATION_JSON_VALUE)
public class FaultProbabilityController {
    @Autowired
    private BayesService bayesService;

    @PostMapping(value= "/findDamage")
    public ResponseEntity<List<ComponentFailureDTO>> estimateValue(@RequestBody List<ComponentFailureDTO> failures) throws InvalidParentException, IOException {
        return new ResponseEntity<>(bayesService.bayes(failures), HttpStatus.OK);
    }
}
