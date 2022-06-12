package com.example.pcstoresystemsupport.service.impl;

import com.example.pcstoresystemsupport.dtos.ComponentEstimationDTO;
import com.example.pcstoresystemsupport.service.MyOntologyService;
import com.example.pcstoresystemsupport.service.RecommendingComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import org.swrlapi.sqwrl.values.SQWRLResultValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<String> motherboards=new ArrayList<>();
        SQWRLResult result= queryEngine.runSQWRLQuery("q1", "Motherboard(?x) ^ motherBoardProducer(?x,?y) " +
                "-> sqwrl:select(?y, ?x) ");
        if (result.next()){
            System.out.println(result);
            System.out.println(result.getColumn(0));
            for(int i=0 ; i< result.getColumn(0).size() ; i++)
                motherboards.add(findDetailsFromQueryWithoutTypes(result.getColumn(0).get(i), i) + " " +
                        result.getColumn(1).get(i).toString().substring(1));
        }
        return motherboards;
    }
    @Override
    public List<String> getProcessors() throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<String> processors=new ArrayList<>();
        SQWRLResult result= queryEngine.runSQWRLQuery("q2", "Generation(?x) -> sqwrl:select(?x) ");
        if (result.next()){
            System.out.println(result);
            System.out.println(result.getColumn(0));
            for(int i=0 ; i< result.getColumn(0).size() ; i++)
                processors.add(result.getColumn(0).get(i).toString().substring(1));
        }
        return processors;
    }

    @Override
    public List<String> findProcessors(String motherboard) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        List<String> processors=new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q3", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsSocket(?x,?z) -> sqwrl:select(?z) ");
        System.out.println("ovo jeeeeeeeeeeeeeeeeeeeeeeee " + result1.getColumn(0).get(0).toString().substring(1));
        String socket=result1.getColumn(0).get(0).toString().substring(1);

        System.out.println("MOTHERBOARD" + motherboard);
        System.out.println("SOCKET" + socket);
        SQWRLResult result2= queryEngine.runSQWRLQuery("q4", "Processor(?x) ^ "+socket+
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
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<String> rams=new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q5", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsRAMSlot(?x,?z) ^ RamSlotCapacity(?x,?cap)-> sqwrl:select(?z,?cap) ");
        String ramSlot=result1.getColumn(0).get(0).toString().substring(1);
        String ramCapacity=findDetailsFromQueryWithoutTypes(result1.getColumn(1).get(0),0);


        SQWRLResult result2= queryEngine.runSQWRLQuery("q6", "RAM(?x) ^ "+ramSlot+"(?y)"+
                "ramTypeIsCompatibleWithSlotType(?x, ?y) ^ramProducer(?x,?producer) ^ ramName(?x, ?name) ^" +
                " ramMemoryCapacity(?x,?capacity) ^swrlb:equal(?capacity,"+ramCapacity+") ^ ramFrequency(?x,?mcs) ->" +
                " sqwrl:select(?x,?producer,?name,?capacity,?mcs) ");

        for(int i=0 ; i< result2.getColumn(0).size() ; i++){

            rams.add("RAM "+findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(i),i) +" "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(2).get(i),i)+
                    " ["+
                    ramSlot+", "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(3).get(i),i)+"GBx1, "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(4).get(i),i)+"MHz"
                    +"]");
        }
        return rams;
    }

    @Override
    public List<String> findRamByMotherBoardAndProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        List<String> rams=new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q7", "Processor(?x) ^ processorName(?x,?y) " +
                "^ swrlb:equal(?y,\""+processor+"\") ^ processorFrequency(?x,?z) -> sqwrl:select(?z) ");
        String proccesorFreq= findDetailsFromQueryWithoutTypes(result1.getColumn(0).get(0),0);


        SQWRLResult result2= queryEngine.runSQWRLQuery("q8", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsRAMSlot(?x,?z) ^ RamSlotCapacity(?x,?cap) -> sqwrl:select(?z,?cap) ");
        String ramSlot=result2.getColumn(0).get(0).toString().substring(1);
        String ramCapacity=findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(0),0);

        SQWRLResult result3= queryEngine.runSQWRLQuery("q9", "RAM(?x) ^ "+ramSlot+"(?y)"+
                "ramTypeIsCompatibleWithSlotType(?x, ?y) ^ramProducer(?x,?producer) ^ ramName(?x, ?name) ^" +
                " ramMemoryCapacity(?x,?capacity) ^swrlb:equal(?capacity,"+ramCapacity+") ^ ramFrequency(?x,?mcs) ^swrlb:lessThan(?mcs,"+proccesorFreq+") ->" +
                " sqwrl:select(?x,?producer,?name,?capacity,?mcs) ");


        for(int i=0 ; i< result3.getColumn(0).size() ; i++){

            rams.add("RAM "+findDetailsFromQueryWithoutTypes(result3.getColumn(1).get(i),i) +" "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(2).get(i),i)+
                    " ["+
                    ramSlot+", "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(3).get(i),i)+"GBx1, "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(4).get(i),i)+"MHz"
                    +"]");
        }
        return rams;
    }

    @Override
    public List<String> findCoolerForProcessor(String motherboard,String processor) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        List<String> coolers=new ArrayList<>();

        SQWRLResult result1= queryEngine.runSQWRLQuery("q43", "Motherboard(?x) ^ motherboardName(?x,?y) " +
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

        for(int i=0 ; i< result3.getColumn(0).size() ; i++){

            coolers.add("CPU_COOLER "+result3.getColumn(0).get(i).toString().substring(1) +
                    " ["+
                    "TDP "+findDetailsFromQueryWithoutTypes(result3.getColumn(1).get(i),i)+"W, "+
                     "Max fan speed "+findDetailsFromQueryWithoutTypes(result3.getColumn(2).get(i),i)+"RMP, "+
                     "Noise level: up to "+findDetailsFromQueryWithoutTypes(result3.getColumn(3).get(i),i)+"dB"+
                    "]");
        }
        return coolers;
    }

    @Override
    public List<String> findCompatibleGraphicCards(String motherboard) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
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

    @Override
    public List<String> findCompatibleOS(String processor) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<String> operatingSystems = new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q14", "Processor(?x) ^ processorName(?x,?y) " +
                "^ swrlb:equal(?y,\""+processor+"\") ^ processorOperatingMode(?x,?z) -> sqwrl:select(?y, ?z) ");

        String operatingMode = result1.getColumn(1).get(0).toString().substring(1);

        SQWRLResult result2= queryEngine.runSQWRLQuery("q15", "OperatingSystem(?x) ^ " + operatingMode +
                "(?y) ^ operatingSystemIsCompatibleWithOperatingMode(?x, ?y)" +
                "-> sqwrl:select(?x) ");

        for(int i=0 ; i< result2.getColumn(0).size() ; i++) {
            operatingSystems.add("OperatingSystem " + result2.getColumn(0).get(i).toString().substring(1));
        }
        return operatingSystems;
    }

    @Override
    public List<String> findCompatibleMonitors(String motherboard) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<String> monitors = new ArrayList<>();
        List<String> distinctMonitors = new ArrayList<>();
        SQWRLResult result1= queryEngine.runSQWRLQuery("q16", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsViewConnectors(?x,?z) -> sqwrl:select(?z) ");
        System.out.println(result1);
        for(int i=0 ; i< result1.getColumn(0).size() ; i++) {
            String viewConnector =  result1.getColumn(0).get(i).toString().substring(1);
            SQWRLResult result2= queryEngine.runSQWRLQuery("q17", "Monitor(?x) ^ " + viewConnector +
                    "(?y) ^ monitorIsCompatibleWithViewConnector(?x, ?y)" +
                    "-> sqwrl:select(?x) ");
            System.out.println(result2);
            for(int j=0 ; j< result2.getColumn(0).size() ; j++) {
                monitors.add("Monitor " + result2.getColumn(0).get(j).toString().substring(1));
            }
        }
        Boolean exists = false;
        for(int j=0 ; j< monitors.size() ; j++) {
            exists = false;
            for(int k=0 ; k< distinctMonitors.size(); k++){
                if(distinctMonitors.get(k).equals(monitors.get(j))) {
                    exists = true;
                    break;
                }
            }
            if(exists == false)
                distinctMonitors.add(monitors.get(j));
        }
        return distinctMonitors;
    }

    @Override
    public List<String> findCompatibleHeadphones(String motherboard) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<String> headphones=new ArrayList<>();

        SQWRLResult combinedConnector= queryEngine.runSQWRLQuery("q18", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsCombinedConnector(?x,?z) -> sqwrl:select(?z) ");

        if(!combinedConnector.getColumn(0).isEmpty()){
            String combined=combinedConnector.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundHeadphones= queryEngine.runSQWRLQuery("q21", "Headphones(?x) ^"+combined+"(?y)"+" headphonesAreCompatibleWithCombinedPort(?x,?y) " +
                    "-> sqwrl:select(?x) ");
            System.out.println("nasaoooo   "+foundHeadphones);
            for(int i=0 ; i< foundHeadphones.getColumn(0).size() ; i++) {
                headphones.add("Headphones " + foundHeadphones.getColumn(0).get(i).toString().substring(1));
            }

        }


        SQWRLResult headphonesAndSpeakersConnector= queryEngine.runSQWRLQuery("q19", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsHeadphoneAndSpeakersConnector(?x,?z) -> sqwrl:select(?z) ");

        if(!headphonesAndSpeakersConnector.getColumn(0).isEmpty()){
            String headphonesAndSpeakers=headphonesAndSpeakersConnector.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundHeadphonesWithSpeakers= queryEngine.runSQWRLQuery("q22", "Headphones(?x) ^"+headphonesAndSpeakers+"(?y)"+" headphonesAreCompatibleWithAudioPort(?x,?y) " +
                    " -> sqwrl:select(?x) ");
            System.out.println("nasaoooo   "+foundHeadphonesWithSpeakers);
            for(int i=0 ; i< foundHeadphonesWithSpeakers.getColumn(0).size() ; i++) {
                headphones.add("Headphones " + foundHeadphonesWithSpeakers.getColumn(0).get(i).toString().substring(1));
            }
        }


        SQWRLResult USBPort= queryEngine.runSQWRLQuery("q20", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsUSBPort(?x,?z) -> sqwrl:select(?z) ");
        if(!USBPort.getColumn(0).isEmpty()){
            String USB=USBPort.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundHeadphonesWithUSB= queryEngine.runSQWRLQuery("q23", "Headphones(?x) ^"+USB+"(?y)"+" headphonesAreCompatibleWithUSBPort(?x,?y) " +
                    " -> sqwrl:select(?x) ");
            System.out.println("nasaoooo   "+foundHeadphonesWithUSB);
            for(int i=0 ; i< foundHeadphonesWithUSB.getColumn(0).size() ; i++) {
                headphones.add("Headphones " + foundHeadphonesWithUSB.getColumn(0).get(i).toString().substring(1));
            }
        }

        return headphones;
    }

    @Override
    public List<String> findCompatibleMicrophones(String motherboard) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        List<String> microphones=new ArrayList<>();

        SQWRLResult microphoneConnector= queryEngine.runSQWRLQuery("q28", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsMicrophoneConnector(?x,?z) -> sqwrl:select(?z) ");

        if(!microphoneConnector.getColumn(0).isEmpty()){
            String microphone=microphoneConnector.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundMicrophones= queryEngine.runSQWRLQuery("q29", "Microphone(?x) ^"+microphone+"(?y)"+" microphoneIsCompatibleWithMicrophonePort(?x,?y) " +
                    "-> sqwrl:select(?x)");
            for(int i=0 ; i< foundMicrophones.getColumn(0).size() ; i++) {
                microphones.add(foundMicrophones.getColumn(0).get(i).toString().substring(1));
            }
        }


        SQWRLResult USBPort= queryEngine.runSQWRLQuery("q30", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsUSBPort(?x,?z) -> sqwrl:select(?z) ");
        if(!USBPort.getColumn(0).isEmpty()){
            String USB=USBPort.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundUSBMic= queryEngine.runSQWRLQuery("q31", "Microphone(?x) ^"+USB+"(?y)"+" microphoneIsCompatibleWithUSBPort(?x,?y) " +
                    "-> sqwrl:select(?x)");
            for(int i=0 ; i< foundUSBMic.getColumn(0).size() ; i++) {
                microphones.add(foundUSBMic.getColumn(0).get(i).toString().substring(1));
            }
        }

        return microphones;
    }

    @Override
    public List<String> findCompatibleSpeakers(String motherboard) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<String> speakers=new ArrayList<>();

        SQWRLResult headphonesAndSpeakersConnector= queryEngine.runSQWRLQuery("q24", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsHeadphoneAndSpeakersConnector(?x,?z) -> sqwrl:select(?z) ");

        if(!headphonesAndSpeakersConnector.getColumn(0).isEmpty()){
            String headphonesAndSpeakers=headphonesAndSpeakersConnector.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundHeadphonesWithSpeakers= queryEngine.runSQWRLQuery("q25", "Speakers(?x) ^"+headphonesAndSpeakers+"(?y)"+" speakersAreCompatibleWithAudioConnector(?x,?y) " +
                    " -> sqwrl:select(?x) ");
            for(int i=0 ; i< foundHeadphonesWithSpeakers.getColumn(0).size() ; i++) {
                speakers.add(foundHeadphonesWithSpeakers.getColumn(0).get(i).toString().substring(1));
            }
        }


        SQWRLResult USBPort= queryEngine.runSQWRLQuery("q26", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsUSBPort(?x,?z) -> sqwrl:select(?z) ");
        if(!USBPort.getColumn(0).isEmpty()){
            String USB=USBPort.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundHeadphonesWithUSB= queryEngine.runSQWRLQuery("q27", "Speakers(?x) ^"+USB+"(?y)"+" speakersAreCompatibleWithUSBPort(?x,?y) " +
                    "-> sqwrl:select(?x) ");
            for(int i=0 ; i< foundHeadphonesWithUSB.getColumn(0).size() ; i++) {
                speakers.add(foundHeadphonesWithUSB.getColumn(0).get(i).toString().substring(1));
            }
        }

        return speakers;
    }

    @Override
    public List<String> findCompatibleMouses(String motherboard) throws SWRLParseException, SQWRLException {

        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        List<String> mouses=new ArrayList<>();

        SQWRLResult PS2MouseConnector= queryEngine.runSQWRLQuery("q32", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsPS/2MousePort(?x,?z) -> sqwrl:select(?z) ");

        if(!PS2MouseConnector.getColumn(0).isEmpty()){
            String PS2Mouse=PS2MouseConnector.getColumn(0).get(0).toString().substring(1);
            SQWRLResult foundPS2Mouses= queryEngine.runSQWRLQuery("q33", "Mouse(?x) ^"+PS2Mouse+"(?y)"+" mouseIsCompatibleWithPS/2MousePort(?x,?y) " +
                    "-> sqwrl:select(?x) ");
            for(int i=0 ; i< foundPS2Mouses.getColumn(0).size() ; i++) {
                mouses.add(foundPS2Mouses.getColumn(0).get(i).toString().substring(1));
            }
        }


        SQWRLResult USBPort= queryEngine.runSQWRLQuery("q34", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsUSBPort(?x,?z) -> sqwrl:select(?z) ");
        if(!USBPort.getColumn(0).isEmpty()){
            String USB=USBPort.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundUSBMouses= queryEngine.runSQWRLQuery("q35", "Mouse(?x) ^"+USB+"(?y)"+" mouseIsCompatibleWithUSBPort(?x,?y) " +
                    " -> sqwrl:select(?x) ");
            for(int i=0 ; i< foundUSBMouses.getColumn(0).size() ; i++) {
                mouses.add(foundUSBMouses.getColumn(0).get(i).toString().substring(1));
            }
        }
        return mouses;
    }

    @Override
    public List<String> findCompatibleKeyboards(String motherboard) throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        List<String> keyboards=new ArrayList<>();

        SQWRLResult PS2KeyboardConnector= queryEngine.runSQWRLQuery("q36", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsPS/2KeyboardPort(?x,?z) -> sqwrl:select(?z) ");

        if(!PS2KeyboardConnector.getColumn(0).isEmpty()){
            String PS2Keyboard=PS2KeyboardConnector.getColumn(0).get(0).toString().substring(1);
            System.out.println("VRATIOOO"+PS2Keyboard);
            SQWRLResult foundPS2Keyboard= queryEngine.runSQWRLQuery("q37", "Keyboard(?x) ^"+PS2Keyboard+"(?y)"+" keyboardIsCompatibleWithPS/2KeyboardPort(?x,?y) " +
                    " -> sqwrl:select(?x) ");
            for(int i=0 ; i< foundPS2Keyboard.getColumn(0).size() ; i++) {
                keyboards.add(foundPS2Keyboard.getColumn(0).get(i).toString().substring(1));
            }
        }


        SQWRLResult USBPort= queryEngine.runSQWRLQuery("q38", "Motherboard(?x) ^ motherboardName(?x,?y) " +
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsUSBPort(?x,?z) -> sqwrl:select(?z) ");
        if(!USBPort.getColumn(0).isEmpty()){
            String USB=USBPort.getColumn(0).get(0).toString().substring(1);

            SQWRLResult foundUSBKeyboards= queryEngine.runSQWRLQuery("q39", "Keyboard(?x) ^"+USB+"(?y)"+" keyboardIsCompatibleWithUSBPort(?x,?y) " +
                    "-> sqwrl:select(?x) ");
            for(int i=0 ; i< foundUSBKeyboards.getColumn(0).size() ; i++) {
                keyboards.add(foundUSBKeyboards.getColumn(0).get(i).toString().substring(1));
            }
        }

        return keyboards;
    }

    @Override
    public List<ComponentEstimationDTO> getGraphicCards() throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<ComponentEstimationDTO> graphicCards= new ArrayList<>();
        SQWRLResult result2= queryEngine.runSQWRLQuery(
                "q40", "GraphicsCard(?x) " +
                        "^graphicCardName(?x, ?name)" +
                        "^graphicCardProducer(?x, ?producer)"+
                        "^minimumPSUForGraphicCard(?x, ?minimumPSU)" +
                        "^memoryTypeOfGraphicCard(?x, ?memoryType)" +
                        "^memorySizeOfGraphicCard(?x, ?memorySize)" +
                        " -> sqwrl:select(?x, ?name, ?producer, ?minimumPSU, ?memoryType, ?memorySize)");

        for(int i=0 ; i< result2.getColumn(0).size() ; i++) {
            graphicCards.add(new ComponentEstimationDTO(
                    "GraphicCard " +
                    findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(i),i) + " " +
                    findDetailsFromQueryWithoutTypes(result2.getColumn(2).get(i),i) + " ["+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(3).get(i),i) + "W, " +
                            findDetailsFromQueryWithoutTypes(result2.getColumn(4).get(i),i)+" "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(5).get(i),i) + "GB "
                    +"]",findDetailsFromQueryWithoutTypes(result2.getColumn(5).get(i),i)));
        }
        return graphicCards;
    }

    @Override
    public List<ComponentEstimationDTO> getRams() throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<ComponentEstimationDTO> rams= new ArrayList<>();
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        SQWRLResult result3= queryEngine.runSQWRLQuery("q41", "RAM(?x) ^ "+
                " ramProducer(?x,?producer) ^ ramName(?x, ?name) ^" +
                " ramMemoryCapacity(?x,?capacity) ^ ramFrequency(?x,?mcs) ->" +
                " sqwrl:select(?x,?producer,?name,?capacity,?mcs) ");


        for(int i=0 ; i< result3.getColumn(0).size() ; i++){
            rams.add(new ComponentEstimationDTO("RAM "+findDetailsFromQueryWithoutTypes(result3.getColumn(1).get(i),i) +" "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(2).get(i),i)+
                    " ["+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(3).get(i),i)+"GBx1, "+
                    findDetailsFromQueryWithoutTypes(result3.getColumn(4).get(i),i)+"MHz"
                    +"]",findDetailsFromQueryWithoutTypes(result3.getColumn(3).get(i),i)));
        }

        return rams;
    }
    @Override
    public List<ComponentEstimationDTO> findMotherboardsForEstimation() throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());
        List<ComponentEstimationDTO> motherboards= new ArrayList<>();
        SQWRLResult result= queryEngine.runSQWRLQuery("q42", "Motherboard(?x) ^ motherBoardProducer(?x,?y) ^ PSU(?x,?usp) " +
                "-> sqwrl:select(?y, ?x, ?usp) ");
        if (result.next()){
            System.out.println(result);
            System.out.println(result.getColumn(0));
            for(int i=0 ; i< result.getColumn(0).size() ; i++)
                motherboards.add(new ComponentEstimationDTO(
                        findDetailsFromQueryWithoutTypes(result.getColumn(0).get(i), i) + " " +
                        result.getColumn(1).get(i).toString().substring(1),findDetailsFromQueryWithoutTypes(result.getColumn(2).get(i), i)));
        }
        return motherboards;
    }
    @Override
    public List<ComponentEstimationDTO> findProcessorsForEstimation() throws SWRLParseException, SQWRLException {
        queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(myOntologyService.createOntology());

        List<ComponentEstimationDTO> processors= new ArrayList<>();

        SQWRLResult result2= queryEngine.runSQWRLQuery("q43", "Processor(?x)"+
                "^ processorName(?x,?name) ^ processorFrequency(?x,?freq) " +
                "^ processorTDP(?x,?tdp) ^ numberOfCores(?x,?noc)-> sqwrl:select(?name,?freq,?tdp,?noc) ");
        System.out.println("vracam"+result2);
        for(int i=0 ; i< result2.getColumn(0).size() ; i++){
            processors.add(new ComponentEstimationDTO(findDetailsFromQueryWithoutTypes(result2.getColumn(0).get(i),i) +" ["+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(3).get(i),i)+ " cores, "+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(i),i)+"MHz,"+
                    "TDP "+findDetailsFromQueryWithoutTypes(result2.getColumn(2).get(i),i)+"W"
                    +"]",findDetailsFromQueryWithoutTypes(result2.getColumn(3).get(i),i)));
        }
        return processors;
    }



}
