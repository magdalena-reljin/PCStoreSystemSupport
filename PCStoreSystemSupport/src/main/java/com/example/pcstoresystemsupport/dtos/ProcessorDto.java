package com.example.pcstoresystemsupport.dtos;

public class ProcessorDto {
    private String name;
    private String motherboardSocket;
    private Integer frequency;
    private Integer tdp;
    private Integer numOfCores;
    private String operatingMode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotherboardSocket() {
        return motherboardSocket;
    }

    public void setMotherboardSocket(String motherboardSocket) {
        this.motherboardSocket = motherboardSocket;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public Integer getNumOfCores() {
        return numOfCores;
    }

    public void setNumOfCores(Integer numOfCores) {
        this.numOfCores = numOfCores;
    }

    public String getOperatingMode() {
        return operatingMode;
    }

    public void setOperatingMode(String operatingMode) {
        this.operatingMode = operatingMode;
    }

    public ProcessorDto(String name, String motherboardSocket, Integer frequency, Integer tdp, Integer numOfCores, String operatingMode) {
        this.name = name;
        this.motherboardSocket = motherboardSocket;
        this.frequency = frequency;
        this.tdp = tdp;
        this.numOfCores = numOfCores;
        this.operatingMode = operatingMode;
    }

    public ProcessorDto() {
    }

    @Override
    public String toString() {
        return "ProcessorDto{" +
                "name='" + name + '\'' +
                ", motherboardSocket='" + motherboardSocket + '\'' +
                ", frequency=" + frequency +
                ", tdp=" + tdp +
                ", numOfCores=" + numOfCores +
                ", operatingMode='" + operatingMode + '\'' +
                '}';
    }
}
