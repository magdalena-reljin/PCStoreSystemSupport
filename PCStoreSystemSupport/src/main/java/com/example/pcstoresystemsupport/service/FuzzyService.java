package com.example.pcstoresystemsupport.service;

import java.util.List;

public interface FuzzyService {
    List<String> fuzzy(String psu, String numOfCores, String memorySizeOfGraphicCard, String ramCapacity);
}
