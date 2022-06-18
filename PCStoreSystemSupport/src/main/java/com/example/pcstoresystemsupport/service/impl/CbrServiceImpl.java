package com.example.pcstoresystemsupport.service.impl;

import com.example.pcstoresystemsupport.connector.CsvConnector;
import com.example.pcstoresystemsupport.dtos.*;
import com.example.pcstoresystemsupport.model.*;
import com.example.pcstoresystemsupport.service.CbrService;
import org.springframework.stereotype.Service;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CbrServiceImpl implements CbrService {

    Connector _connector;  /** Connector object */
    CBRCaseBase _caseBase;  /** CaseBase object */

    NNConfig simConfig;  /** KNN configuration */
    public static List<PC> pcs= new ArrayList<>();


    public void configure() throws ExecutionException {
        System.out.println("CONFIGURE ");
        System.out.println("CONFIGURE PC LIST  "+getPcs().size());
        _connector =  new CsvConnector(this.pcs);

        _caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization

        simConfig = new NNConfig(); // KNN configuration
        simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average

        // simConfig.addMapping(new Attribute("attribute", CaseDescription.class), new Interval(5));
        // TODO

        // Equal - returns 1 if both individuals are equal, otherwise returns 0
        // Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval)
        // Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case
        // EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
        // MaxString - returns a similarity value depending of the biggest substring that belong to both strings
        // EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
        // EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
        // Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
        // TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity)
    }

    public void cycle(CBRQuery query) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
        eval = SelectCases.selectTopKRR(eval, 5);
        System.out.println("Retrieved cases:");
        for (RetrievalResult nse : eval)
            System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
    }

    public void postCycle() throws ExecutionException {

    }



    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        for (CBRCase c: cases)
            System.out.println(c.getDescription());
        return _caseBase;
    }

    public List<PC> getPcs() {
        return this.pcs;
    }

    public void setPcs(List<PC> pcs) {
        this.pcs = pcs;
    }

    @Override
    public List<String> startCbr(PCDto pcDto, List<PC> myPCs) {
        StandardCBRApplication recommender = new CbrServiceImpl();
        try {
            setPcs(myPCs);
            if(this.pcs.size()>0)
            recommender.configure();

            recommender.preCycle();

            CBRQuery query = new CBRQuery();
            MotherboardDto motherboardDto=pcDto.getMotherboard();
            ProcessorDto processorDto=pcDto.getProcessor();
            RamDto ramDto=pcDto.getRam();
            GpuDto gpuDto=pcDto.getGpu();
            CpuCoolerDto cpuCoolerDto=pcDto.getCooler();
            Motherboard motherboard=new Motherboard(motherboardDto.getName(),motherboardDto.getProducer(),motherboardDto.getRamSlotCapacity(),motherboardDto.getMaxInternalMemory(),motherboardDto.getNumOfMemorySlots(),motherboardDto.getRamSlotType(),motherboardDto.getSocket(),motherboardDto.getGpuSlot());
            Processor processor=new Processor(processorDto.getName(),processorDto.getMotherboardSocket(),processorDto.getFrequency(),processorDto.getTdp(),processorDto.getNumOfCores(),processorDto.getOperatingMode());
            Ram ram=new Ram(ramDto.getName(),ramDto.getProducer(),ramDto.getCapacity(),ramDto.getFrequency(),ramDto.getType());
            Gpu gpu=new Gpu(gpuDto.getName(),gpuDto.getMemorySize(),gpuDto.getMemoryType(),gpuDto.getMinPSU());
            CpuCooler cooler=new CpuCooler(cpuCoolerDto.getName(),cpuCoolerDto.getTdp(),cpuCoolerDto.getMaxFanSpeed(),cpuCoolerDto.getNoiseLevel());
            PC newCase=new PC(motherboard,processor,gpu,cooler,ram);
            // TODO
            query.setDescription( newCase );

            recommender.cycle(query);

            recommender.postCycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
