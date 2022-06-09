package com.example.pcstoresystemsupport.service;

import com.example.pcstoresystemsupport.dtos.ComponentEstimationDTO;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.util.List;
import java.util.Map;

public interface RecommendingComponentsService {
    List<String> getMotherboards() throws SWRLParseException, SQWRLException;
    List<String> getProcessors() throws SWRLParseException, SQWRLException;
    List<String> findRamByMotherBoard(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findProcessors(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findRamByMotherBoardAndProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException;
    List<String> findCoolerForProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleGraphicCards(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleOS(String processor) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleMonitors(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleHeadphones(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleMicrophones(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleSpeakers(String motherboard) throws SWRLParseException, SQWRLException;
    List<String> findCompatibleMouses(String motherboard)throws SWRLParseException, SQWRLException;
    List<String> findCompatibleKeyboards(String motherboard)throws SWRLParseException, SQWRLException;

    List<ComponentEstimationDTO> getGraphicCards() throws SWRLParseException, SQWRLException;
    List<ComponentEstimationDTO> getRams() throws SWRLParseException, SQWRLException;
    List<ComponentEstimationDTO>  findProcessorsForEstimation() throws SWRLParseException, SQWRLException;
    List<ComponentEstimationDTO>  findMotherboardsForEstimation() throws SWRLParseException, SQWRLException;
}
