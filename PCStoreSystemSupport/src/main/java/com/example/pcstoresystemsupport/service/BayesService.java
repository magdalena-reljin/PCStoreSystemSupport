package com.example.pcstoresystemsupport.service;

import com.example.pcstoresystemsupport.dtos.ComponentFailureDTO;
import unbbayes.prs.exception.InvalidParentException;

import java.io.IOException;
import java.util.List;

public interface BayesService {
    List<ComponentFailureDTO> bayes(List<ComponentFailureDTO>failures) throws InvalidParentException, IOException;
}
