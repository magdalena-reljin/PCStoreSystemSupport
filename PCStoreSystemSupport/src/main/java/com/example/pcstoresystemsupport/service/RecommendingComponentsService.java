package com.example.pcstoresystemsupport.service;

import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.util.List;

public interface RecommendingComponentsService {
    List<String> getMotherboards() throws SWRLParseException, SQWRLException;
    List<String> getProcessors() throws SWRLParseException, SQWRLException;
    List<String> findRamByMotherBoard(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findProcessors(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findRamByMotherBoardAndProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException;
    List<String> findCoolerForProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleGraphicCards(String motherboard) throws SWRLParseException, SQWRLException;

    List<String> findCompatibleOS(String processor) throws SWRLParseException, SQWRLException;
}
