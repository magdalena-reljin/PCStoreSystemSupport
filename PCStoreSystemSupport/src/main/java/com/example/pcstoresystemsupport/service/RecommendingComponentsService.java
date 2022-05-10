package com.example.pcstoresystemsupport.service;

import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.util.List;

public interface RecommendingComponentsService {
    List<String> getMotherboards() throws SWRLParseException, SQWRLException;
    List<String> getProcessors() throws SWRLParseException, SQWRLException;

    List<String> findProcessors(String motherboard) throws SWRLParseException, SQWRLException;
}
