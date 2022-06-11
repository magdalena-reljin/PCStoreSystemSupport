package com.example.pcstoresystemsupport.service.impl;

import com.example.pcstoresystemsupport.dtos.ComponentFailureDTO;
import com.example.pcstoresystemsupport.service.BayesService;
import org.springframework.stereotype.Service;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BayesServiceImpl implements BayesService {

    @Override
    public List<ComponentFailureDTO> bayes(List<ComponentFailureDTO> failures) throws InvalidParentException, IOException {
        List<ComponentFailureDTO> results= new ArrayList<>();

        ProbabilisticNetwork net = new ProbabilisticNetwork("bayes");

        BaseIO io = new NetIO();
        net = (ProbabilisticNetwork)io.load(new File(System.getProperty("user.dir")+"/data/bayes.net"));

        // compiling
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        // states overview
        List<Node> nodeList = net.getNodes();
       for (Node node: nodeList) {
           System.out.println(node.getName());
           for (int i = 0; i < node.getStatesSize(); i++) {
               System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
           }
       }
      System.out.println("*************************************** after evidence");
        // adding an evidence
        for(ComponentFailureDTO failure: failures){
            ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(failure.getFailure());
            int stateIndex = 0; // index of state "green"
            factNode.addFinding(stateIndex);

        }

        // propagation
        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // states overview after propagation
        for (Node node : nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
                if(node.getStateAt(i).equals("yes") && node.getName().toUpperCase().endsWith("DAMAGE")) {
                    results.add(new ComponentFailureDTO(node.getName().toUpperCase().replace("_", " ") ,((ProbabilisticNode) node).getMarginalAt(i)*100));
                }
            }
        }
         //saving to file
         new NetIO().save(new File(System.getProperty("user.dir")+"/data/results.net"), net);
         return results;
    }


}
