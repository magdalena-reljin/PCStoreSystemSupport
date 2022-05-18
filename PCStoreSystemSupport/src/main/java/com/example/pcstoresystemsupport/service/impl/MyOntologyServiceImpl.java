package com.example.pcstoresystemsupport.service.impl;

import com.example.pcstoresystemsupport.service.MyOntologyService;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.stereotype.Service;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.io.File;
import java.util.Optional;

@Service
public class MyOntologyServiceImpl implements MyOntologyService {

    @Override
    public OWLOntology createOntology() {
        OWLOntology ontology=null;
        Optional<String> owlFilename = Optional.of(System.getProperty("user.dir")+"/ontology.owl");
        Optional<File> owlFile = (owlFilename != null && owlFilename.isPresent()) ? Optional.of(new File(owlFilename.get())) : Optional.<File>empty();
        System.out.println("ja sam ovde");

        try {
            // Create an OWL ontology using the OWLAPI
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
            ontology = owlFile.isPresent() ?
                    ontologyManager.loadOntologyFromOntologyDocument(owlFile.get()) :
                    ontologyManager.createOntology();
            System.out.println("kreirao sam ontologiju");
            System.out.println("......");




        } catch (OWLOntologyCreationException e) {
            System.err.println("Error creating OWL ontology: " + e.getMessage());
            System.exit(-1);
        }
        return ontology;
    }
}
