package com.example.pcstoresystemsupport.service.impl;

import com.example.pcstoresystemsupport.service.MyOntologyService;
import com.example.pcstoresystemsupport.service.RecommendingComponentsService;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import org.swrlapi.sqwrl.values.SQWRLResultValue;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendingComponentsServiceImpl implements RecommendingComponentsService {
    private SQWRLQueryEngine queryEngine;

    @Autowired
    private MyOntologyService myOntologyService;

    public RecommendingComponentsServiceImpl(MyOntologyService myOntologyService ){
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
    }
    @Override
    public List<String> getMotherboards() throws SWRLParseException, SQWRLException {
        List<String> motherboards=new ArrayList<>();
        SQWRLResult result= queryEngine.runSQWRLQuery("q1", "Motherboard(?x) -> sqwrl:select(?x) ");
        if (result.next()){
            System.out.println("********sve maticne********");
            System.out.println(result);
            System.out.println(result.getColumn(0));
            for(int i=0 ; i< result.getColumn(0).size() ; i++)
                motherboards.add(result.getColumn(0).get(i).toString().substring(1));
        }
        return motherboards;
    }
    @Override
    public List<String> getProcessors() throws SWRLParseException, SQWRLException {
        List<String> processors=new ArrayList<>();
        SQWRLResult result= queryEngine.runSQWRLQuery("q2", "Generation(?x) -> sqwrl:select(?x) ");
        if (result.next()){
            System.out.println("********svi procesori********");
            System.out.println(result);
            System.out.println(result.getColumn(0));
            for(int i=0 ; i< result.getColumn(0).size() ; i++)
                processors.add(result.getColumn(0).get(i).toString().substring(1));
        }
        return processors;
    }

    @Override
    public List<String> findProcessors(String motherboard) throws SWRLParseException, SQWRLException {
        List<String> processors=new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q3", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsSocket(?x,?z) -> sqwrl:select(?z) ");
        String socket=result1.getColumn(0).get(0).toString().substring(1);

        SQWRLResult result2= queryEngine.runSQWRLQuery("q4", "Generation(?x) ^ "+socket+
                "(?y) ^ processorIsCompatibleWithSocket(?x, ?y) ^ processorName(?x,?name) ^ processorFrequency(?x,?freq) " +
                "^ processorTDP(?x,?tdp)-> sqwrl:select(?name,?freq,?tdp) ");
        System.out.println("vracam"+result2);
        for(int i=0 ; i< result2.getColumn(0).size() ; i++){
            processors.add("Processor "+findDetailsFromQueryWithoutTypes(result2.getColumn(0).get(i),i) +" "+
                    " ["+socket+","+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(i),i)+"MHz,"+
                    "TDP "+findDetailsFromQueryWithoutTypes(result2.getColumn(2).get(i),i)+"W"
                    +"]");
        }
        return processors;
    }
    private String findDetailsFromQueryWithoutTypes(SQWRLResultValue result2, int i){
        return result2.toString().substring(result2.toString().indexOf("\"")+1,result2.toString().lastIndexOf("\""));
    }

    @Override
    public List<String> findRamByMotherBoard(String motherboard) throws SWRLParseException, SQWRLException {
        List<String> rams=new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q5", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsRAMSlot(?x,?z) -> sqwrl:select(?z) ");
        String ramSlot=result1.getColumn(0).get(0).toString().substring(1);

        SQWRLResult result2= queryEngine.runSQWRLQuery("q6", "RAM(?x) ^ "+ramSlot+"(?y)"+
                "ramTypeIsCompatibleWithSlotType(?x, ?y) ^ramProducer(?x,?producer) ^ ramName(?x, ?name) ^" +
                " ramMemoryCapacity(?x,?capacity) ^ memoryClockSpeed(?x,?mcs) ->" +
                " sqwrl:select(?x,?producer,?name,?capacity,?mcs) ");
        System.out.println("vracam"+result2);
        for(int i=0 ; i< result2.getColumn(0).size() ; i++){

            rams.add("RAM "+findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(i),i) +" "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(2).get(i),i)+
                    " ["+
                    result2.getColumn(0).get(i).toString().substring(1)+", "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(3).get(i),i)+"GBx1, "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(4).get(i),i)+"MHz"
                    +"]");
        }
        return rams;
    }

    @Override
    public List<String> findRamByMotherBoardAndProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException {
        List<String> rams=new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q7", "Generation(?x) ^ processorName(?x,?y) " +
                "^ swrlb:equal(?y,\""+processor+"\") ^ processorFrequency(?x,?z) -> sqwrl:select(?z) ");
        String proccesorFreq= findDetailsFromQueryWithoutTypes(result1.getColumn(0).get(0),0);
        System.out.println("FREKVENCIJAAA"+proccesorFreq);


        SQWRLResult result2= queryEngine.runSQWRLQuery("q8", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsRAMSlot(?x,?z) -> sqwrl:select(?z) ");
        String ramSlot=result2.getColumn(0).get(0).toString().substring(1);
        System.out.println("vracam  ram slooot"+ramSlot);

        SQWRLResult result3= queryEngine.runSQWRLQuery("q9", "RAM(?x) ^ "+ramSlot+"(?y)"+
                "ramTypeIsCompatibleWithSlotType(?x, ?y) ^ramProducer(?x,?producer) ^ ramName(?x, ?name) ^" +
                " ramMemoryCapacity(?x,?capacity) ^ memoryClockSpeed(?x,?mcs) ^swrlb:lessThan(?mcs,"+proccesorFreq+") ->" +
                " sqwrl:select(?x,?producer,?name,?capacity,?mcs) ");
        System.out.println("vracam kompatibiln ram"+result3);

        for(int i=0 ; i< result3.getColumn(0).size() ; i++){

            rams.add("RAM "+findDetailsFromQueryWithoutTypes(result3.getColumn(1).get(i),i) +" "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(2).get(i),i)+
                    " ["+
                    result3.getColumn(0).get(i).toString().substring(1)+", "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(3).get(i),i)+"GBx1, "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(4).get(i),i)+"MHz"
                    +"]");
        }
        return rams;
    }

    @Override
    public List<String> findCoolerForProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException {
        List<String> coolers=new ArrayList<>();

        SQWRLResult result1= queryEngine.runSQWRLQuery("q3", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsSocket(?x,?z) -> sqwrl:select(?z) ");
        String socket=result1.getColumn(0).get(0).toString().substring(1);

        SQWRLResult result2= queryEngine.runSQWRLQuery("q10", "Generation(?x) ^ processorName(?x,?y) " +
                "^ swrlb:equal(?y,\""+processor+"\") ^ processorTDP(?x,?z) -> sqwrl:select(?z) ");
        String proccesorTDP= findDetailsFromQueryWithoutTypes(result2.getColumn(0).get(0),0);
        System.out.println("PROCESOOR TDP "+proccesorTDP);



        SQWRLResult result3= queryEngine.runSQWRLQuery("q11", "CpuCooler(?x) ^"+socket+"(?y)"+" coolerIsCompatibleWithSocket(?x,?y) ^" +
                "coolerTDP(?x,?cooler)" +
                "^ swrlb:greaterThan(?cooler,"+proccesorTDP+") ^ maxFanSpeed(?x,?speed) ^ noiseLevel(?x,?level) -> sqwrl:select(?x,?cooler,?speed,?level) ");
        //String ramSlot=result2.getColumn(0).get(0).toString().substring(1);
        System.out.println("vracam  ram slooot"+result3);



        for(int i=0 ; i< result3.getColumn(0).size() ; i++){

            coolers.add("CPU_COOLER "+result3.getColumn(0).get(i).toString().substring(1) +
                    " ["+
                    "TDP "+findDetailsFromQueryWithoutTypes(result3.getColumn(1).get(i),i)+"W, "+
                     "Max fan speed "+findDetailsFromQueryWithoutTypes(result3.getColumn(2).get(i),i)+"RMP, "+
                     "Noise level: up to "+findDetailsFromQueryWithoutTypes(result3.getColumn(2).get(i),i)+"dB"+
                    "]");
        }
        return coolers;
    }

    @Override
    public List<String> findCompatibleGraphicCards(String motherboard) throws SWRLParseException, SQWRLException {
        List<String> graphicCards=new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q12", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsGraphicCardSlots(?x,?z) ^ PSU(?x, ?PSUvalue)" +
                " -> sqwrl:select(?z, ?PSUvalue) ");

        String graphicCardSlot=result1.getColumn(0).get(0).toString().substring(1);
        String PSU = findDetailsFromQueryWithoutTypes(result1.getColumn(1).get(0), 1);

        SQWRLResult result2= queryEngine.runSQWRLQuery(
                "q13", "GraphicsCard(?x) ^ " + graphicCardSlot + "(?y)" +
                "graphicCardIsCompatibleWithGraphicCardSlot(?x, ?y)" +
                "^graphicCardName(?x, ?name)" +
                "^graphicCardProducer(?x, ?producer)"+
                "^minimumPSUForGraphicCard(?x, ?minimumPSU)" +
                "^ swrlb:lessThan(?minimumPSU," + Double.parseDouble(PSU)*0.4 +")" +
                "^memoryTypeOfGraphicCard(?x, ?memoryType)" +
                "^memorySizeOfGraphicCard(?x, ?memorySize)" +
                " -> sqwrl:select(?x, ?producer, ?name, ?minimumPSU, ?memoryType, ?memorySize)");

        for(int i=0 ; i< result2.getColumn(0).size() ; i++) {
            graphicCards.add("GraphicCard " +
            findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(i),i) + " " +
            findDetailsFromQueryWithoutTypes(result2.getColumn(2).get(i),i) +
            " ["+ graphicCardSlot+ ", " +
            findDetailsFromQueryWithoutTypes(result2.getColumn(3).get(i),i) + "W, " +
            findDetailsFromQueryWithoutTypes(result2.getColumn(5).get(i),i) + "GB, " +
            findDetailsFromQueryWithoutTypes(result2.getColumn(4).get(i),i)
            +"]");
        }
        return graphicCards;
    }
}
