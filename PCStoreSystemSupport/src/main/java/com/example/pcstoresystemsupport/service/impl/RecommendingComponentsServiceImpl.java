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
                "^ swrlb:equal(?y,\""+motherboard+"\") ^ containsMotherboardElements(?x,?z) -> sqwrl:select(?z) ");
        String socket=result1.getColumn(0).get(0).toString().substring(1);

        SQWRLResult result2= queryEngine.runSQWRLQuery("q4", "Generation(?x) ^ "+socket+
                "(?y) ^ processorIsCompatibleWithSocket(?x, ?y) ^ processorName(?x,?name) ^ processorFrequency(?x,?freq) " +
                "^ processorTDP(?x,?tdp)-> sqwrl:select(?name,?freq,?tdp) ");
        System.out.println("vracam"+result2);
        for(int i=0 ; i< result2.getColumn(0).size() ; i++){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+result2.getColumn(1).get(i).toString()
                    .substring(result2.getColumn(1).get(i).toString().indexOf("\"")+1,result2.getColumn(1).get(i).toString().lastIndexOf("\""))
                    );
            processors.add("Processor "+findDetailsFromQueryWithoutTypes(result2.getColumn(0).get(i),i) +" "+
                    " ["+socket+","+
                    findDetailsFromQueryWithoutTypes(result2.getColumn(1).get(i),i)+"GHz,"+
                    "TDP "+findDetailsFromQueryWithoutTypes(result2.getColumn(2).get(i),i)+"W"
                    +"]");
        }
        return processors;
    }
    private String findDetailsFromQueryWithoutTypes(SQWRLResultValue result2, int i){
        return result2.toString().substring(result2.toString().indexOf("\"")+1,result2.toString().lastIndexOf("\""));
    }
}
