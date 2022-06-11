package com.example.pcstoresystemsupport.service.impl;

import com.example.pcstoresystemsupport.service.FuzzyService;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.springframework.stereotype.Service;

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
            Variable homeAdvanced = block.getVariable("home_advanced");
            Variable work = block.getVariable("working");
            Variable students = block.getVariable("students");
            Variable gaming = block.getVariable("gaming");
            Variable mining = block.getVariable("mining");
            Variable programming = block.getVariable("programming");
            Variable modelling3D = block.getVariable("modeling3D");
            Variable graphicEditing = block.getVariable("graphic_editing");
            Variable musicComposing  = block.getVariable("music_composing");

            results.add("HOME USE:          "+home.getValue());
            results.add("ADVANCED HOME USE: "+homeAdvanced.getValue());
            results.add("WORK USE:          "+work.getValue());
            results.add("STUDENT USE:       "+students.getValue());
            results.add("GAMING:            "+gaming.getValue());
            results.add("DATA MINING:       "+mining.getValue());
            results.add("PROGRAMMING:       "+programming.getValue());
            results.add("3D MODELLING:      "+modelling3D.getValue());
            results.add("GRAPHIC EDITING:   "+graphicEditing.getValue());
            results.add("COMPOSING MUSIC:   "+musicComposing.getValue());

            return results;
        } catch (Exception e) {
            System.err.println(e);
            return results;
        }
    }


}
