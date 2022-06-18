package com.example.pcstoresystemsupport.connector;

import com.example.pcstoresystemsupport.dtos.PCDto;
import com.example.pcstoresystemsupport.model.PC;
import com.example.pcstoresystemsupport.service.RecommendingComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CsvConnector implements Connector {
    private SQWRLQueryEngine queryEngine;
    private List<PC> pcs=new ArrayList<>();
    public CsvConnector(List<PC> pcs1) {
        System.out.println("CsvConnector LIST size:  "+pcs1.size());
        for(PC pc: pcs1)
         this.pcs.add(pc);
    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
        System.out.println("LISTA U CSV KONEKTORU   "+this.pcs.size());
        System.out.println("*******************************************************");

        for(PC pc: this.pcs){
            CBRCase cbrCase = new CBRCase();
            cbrCase.setDescription(pc);
            cases.add(cbrCase);
        }

        return cases;
    }
    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
        return null;
    }

    @Override
    public void storeCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void close() {
    }

    @Override
    public void deleteCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void initFromXMLfile(URL arg0) throws InitializingException {
    }
}
