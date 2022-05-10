package com.example.pcstoresystemsupport.controller;

import com.example.pcstoresystemsupport.service.RecommendingComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/recommendingComponents", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendingComponentsController {
    @Autowired
    private RecommendingComponentsService recommendingComponentsService;

    @GetMapping(value= "/motherboards")
    public ResponseEntity<List<String>> getMotherboards() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.getMotherboards(), HttpStatus.OK);
    }
    @GetMapping(value= "/processors")
    public ResponseEntity<List<String>> getProcessors() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.getProcessors(), HttpStatus.OK);
    }
    @PostMapping(value= "/findProcessors/{motherboard}")
    public ResponseEntity<List<String>> findProcessors(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findProcessors(motherboard), HttpStatus.OK);
    }
}
