package com.example.pcstoresystemsupport.service.impl;

import com.example.pcstoresystemsupport.service.FuzzyService;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FuzzyServiceImpl implements FuzzyService {
    private static final String fileName = System.getProperty("user.dir")+"/data/analiza.fcl";

    @Override
    public List<String> fuzzy(String psu, String numOfCores, String memorySizeOfGraphicCard, String ramCapacity) {

        List<String> results=new ArrayList<>();

        try {
            FIS filePath = FIS.load(fileName, true);
            FunctionBlock block = filePath.getFunctionBlock("component_value_estimation");

            block.getRuleBlocks();
            block.setVariable("powerSupply", Double.parseDouble(psu));
            block.setVariable("numberOfCores", Integer.parseInt(numOfCores));
            block.setVariable("ramMemoryCapacity", Integer.parseInt(ramCapacity));
            block.setVariable("memorySizeOfGraphicCard", Integer.parseInt(memorySizeOfGraphicCard));

            filePath.evaluate();

            Variable home = block.getVariable("home");
            Variable work = block.getVariable("working");
            Variable gaming = block.getVariable("gaming");
            Variable mining = block.getVariable("dataMining");
            Variable programming = block.getVariable("programming");
            Variable modelling3D = block.getVariable("graphicEditingOr3Dmodeling");

            DecimalFormat df=new DecimalFormat("####0.0");
            results.add("HOME USE:          "+df.format(home.getValue()));
            results.add("WORK USE:          "+df.format(work.getValue()));
            results.add("GAMING:            "+df.format(gaming.getValue()));
            results.add("DATA MINING:       "+df.format(mining.getValue()));
            results.add("PROGRAMMING:       "+df.format(programming.getValue()));
            results.add("GRAPHIC EDITING OR 3D MODELLING:      "+df.format(modelling3D.getValue()));

            return results;
        } catch (Exception e) {
            System.err.println(e);
            return results;
        }
    }


}
