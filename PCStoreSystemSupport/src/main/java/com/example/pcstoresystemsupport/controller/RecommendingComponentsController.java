package com.example.pcstoresystemsupport.controller;

import com.example.pcstoresystemsupport.dtos.*;
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
import java.util.Map;

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
    @GetMapping(value= "/graphicCards")
    public ResponseEntity<List<ComponentEstimationDTO>> getGraphicCards() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.getGraphicCards(), HttpStatus.OK);
    }
    @GetMapping(value= "/motherboardsForEstimation")
    public ResponseEntity<List<ComponentEstimationDTO>> getMotherboardsForEstimation() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findMotherboardsForEstimation(), HttpStatus.OK);
    }
    @GetMapping(value= "/processorsForEstimation")
    public ResponseEntity<List<ComponentEstimationDTO>> getProcessorsForEstimation() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findProcessorsForEstimation(), HttpStatus.OK);
    }
    @GetMapping(value= "/rams")
    public ResponseEntity<List<ComponentEstimationDTO>> getRams() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.getRams(), HttpStatus.OK);
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
    @PostMapping(value= "/findCompatibleMouses/{motherboard}")
    public ResponseEntity<List<String>> findCompatibleMouses(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleMouses(motherboard), HttpStatus.OK);
    }
    @PostMapping(value= "/findCompatibleKeyboards/{motherboard}")
    public ResponseEntity<List<String>> findCompatibleKeyboards(@PathVariable("motherboard") String motherboard) throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCompatibleKeyboards(motherboard), HttpStatus.OK);
    }

    @GetMapping(value= "/processorsForCbr")
    public ResponseEntity<List<ProcessorDto>> processorsForCbr() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findProcessorsForCbr(), HttpStatus.OK);
    }
    @GetMapping(value= "/motherboardsForCbr")
    public ResponseEntity<List<MotherboardDto>> motherboardsForCbr() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findMotherboardsForCbr(), HttpStatus.OK);
    }
    @GetMapping(value= "/ramsForCbr")
    public ResponseEntity<List<RamDto>> ramsForCbr() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findRamsForCbr(), HttpStatus.OK);
    }
    @GetMapping(value= "/gpusForCbr")
    public ResponseEntity<List<GpuDto>> gpusForCbr() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findGpusForCbr(), HttpStatus.OK);
    }
    @GetMapping(value= "/coolersForCbr")
    public ResponseEntity<List<CpuCoolerDto>> coolersForCbr() throws SWRLParseException, SQWRLException {
        return new ResponseEntity<>(recommendingComponentsService.findCoolersForCbr(), HttpStatus.OK);
    }
}
