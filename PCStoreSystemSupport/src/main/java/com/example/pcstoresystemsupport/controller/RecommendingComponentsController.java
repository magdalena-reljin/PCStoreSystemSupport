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
    @PostMapping(value= "/findRamByMotherboard/{motherboard}")
    public ResponseEntity<List<String>> findRamByMotherboard(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findRamByMotherBoard(motherboard), HttpStatus.OK);
    }
    @PostMapping(value= "/findRamByMotherboardAndProcessor/{motherboard}/{processor}")
    public ResponseEntity<List<String>> findRamByMotherboard(@PathVariable("motherboard") String motherboard,@PathVariable("processor") String processor) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findRamByMotherBoardAndProcessor(motherboard, processor), HttpStatus.OK);
    }

    @PostMapping(value= "/findCoolerForProcessor/{motherboard}/{processor}")
    public ResponseEntity<List<String>> findCoolerForProcessor(@PathVariable("motherboard") String motherboard,@PathVariable("processor") String processor) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCoolerForProcessor(motherboard, processor), HttpStatus.OK);
    }

    @PostMapping(value= "/findCompatibleGraphicCards/{motherboard}")
    public ResponseEntity<List<String>> findCompatibleGraphicCards(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleGraphicCards(motherboard), HttpStatus.OK);
    }

    @PostMapping(value= "/findCompatibleOS/{processor}")
    public ResponseEntity<List<String>> findCompatibleOS(@PathVariable("processor") String processor) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleOS(processor), HttpStatus.OK);
    }

    @PostMapping(value= "/findCompatibleMonitors/{motherboard}")
    public ResponseEntity<List<String>> findCompatibleMonitors(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleMonitors(motherboard), HttpStatus.OK);
    }

    @PostMapping(value= "/findCompatibleHeadphones/{motherboard}")
    public ResponseEntity<List<String>> findCompatibleHeadphones(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleHeadphones(motherboard), HttpStatus.OK);
    }
    @PostMapping(value= "/findCompatibleMicrophones/{motherboard}")
    public ResponseEntity<List<String>> findCompatibleMicrophones(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleMicrophones(motherboard), HttpStatus.OK);
    }
    @PostMapping(value= "/findCompatibleSpeakers/{motherboard}")
    public ResponseEntity<List<String>> findCompatibleSpeakers(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleSpeakers(motherboard), HttpStatus.OK);
    }
}
