package com.example.pcstoresystemsupport.controller;

import com.example.pcstoresystemsupport.dtos.ComponentFailureDTO;
import com.example.pcstoresystemsupport.service.BayesService;
import com.example.pcstoresystemsupport.service.CbrService;
import com.example.pcstoresystemsupport.service.RecommendingComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import unbbayes.prs.exception.InvalidParentException;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/cbr", produces = MediaType.APPLICATION_JSON_VALUE)
public class CbrController {
    @Autowired
    private CbrService cbrService;
    @Autowired
    private RecommendingComponentsService recommendingComponentsService;

    @PostMapping(value= "/findSimilarPCs")
    public ResponseEntity<List<ComponentFailureDTO>> estimateValue(@RequestBody List<ComponentFailureDTO> failures) throws SWRLParseException, SQWRLException {
        recommendingComponentsService.findPcs();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value= "/proba")
    public ResponseEntity<List<ComponentFailureDTO>> proba() throws SWRLParseException, SQWRLException {
        recommendingComponentsService.findPcs();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
