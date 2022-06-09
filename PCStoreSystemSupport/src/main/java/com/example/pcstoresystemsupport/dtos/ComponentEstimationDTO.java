package com.example.pcstoresystemsupport.dtos;

public class ComponentEstimationDTO {
    private String componentName;
    private String paramForEstimation;

    public ComponentEstimationDTO() {
    }

    public ComponentEstimationDTO(String componentName, String paramForEstimation) {
        this.componentName = componentName;
        this.paramForEstimation = paramForEstimation;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getParamForEstimation() {
        return paramForEstimation;
    }

    public void setParamForEstimation(String paramForEstimation) {
        this.paramForEstimation = paramForEstimation;
    }
}
