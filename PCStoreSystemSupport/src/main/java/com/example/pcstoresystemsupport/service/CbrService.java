package com.example.pcstoresystemsupport.service;

import com.example.pcstoresystemsupport.dtos.PCDto;
import com.example.pcstoresystemsupport.model.PC;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;

import java.util.List;

public interface CbrService extends StandardCBRApplication {
    public List<String> startCbr(PCDto pcDto,List<PC> pcs);
}
