package com.example.pcstoresystemsupport.controller;

import com.example.pcstoresystemsupport.dtos.ComponentFailureDTO;
import com.example.pcstoresystemsupport.dtos.PCDto;
import com.example.pcstoresystemsupport.model.*;
import com.example.pcstoresystemsupport.service.BayesService;
import com.example.pcstoresystemsupport.service.CbrService;
import com.example.pcstoresystemsupport.service.RecommendingComponentsService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import unbbayes.prs.exception.InvalidParentException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
    public ResponseEntity<List<String>> estimateValue(@RequestBody PCDto pcDto) throws SWRLParseException, SQWRLException, IOException {
       // System.out.println(pcDto);
//       // ObjectMapper mapper = new ObjectMapper();


       //Object to JSON in file
        //mapper.writeValue(new File(System.getProperty("user.dir")+"/data/a.json"),new PC(new Motherboard(),new Processor(),new Gpu(),new CpuCooler(),new Ram()) );

//Object to JSON in String
        //String jsonInString = mapper.writeValueAsString(user);
        cbrService.startCbr(pcDto,recommendingComponentsService.findPcs());
        return null;
    }
}
