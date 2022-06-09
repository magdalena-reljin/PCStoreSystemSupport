package com.example.pcstoresystemsupport.controller;

import com.example.pcstoresystemsupport.service.FuzzyService;
import com.example.pcstoresystemsupport.service.RecommendingComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/componentValueEstimationController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComponentValueEstimationController {
    @Autowired
    private FuzzyService fuzzyService;

    @PostMapping(value= "/estimateValue/{psu}/{numOfCores}/{memorySizeOfGraphicCard}/{ramCapacity}")
    public ResponseEntity<List<String>> estimateValue(@PathVariable("psu") String psu,@PathVariable("numOfCores") String numOfCores,
                                                      @PathVariable("memorySizeOfGraphicCard") String memorySizeOfGraphicCard, @PathVariable("ramCapacity") String ramCapacity) {

        System.out.println("psu "+psu);
        System.out.println("numOfCores "+numOfCores);
        System.out.println("memorySizeOfGraphicCard "+memorySizeOfGraphicCard);
        System.out.println("ramCapacity "+ramCapacity);
        return new ResponseEntity<>(fuzzyService.fuzzy(psu,numOfCores,memorySizeOfGraphicCard,ramCapacity), HttpStatus.OK);
    }
}
