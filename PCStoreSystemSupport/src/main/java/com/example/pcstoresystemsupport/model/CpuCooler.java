package com.example.pcstoresystemsupport.model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CpuCooler implements CaseComponent {
    @Override
    public Attribute getIdAttribute() {
        return null;
    }
    private String name;
    private Integer tdp;
    private Integer maxFanSpeed;
    private Integer noiseLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public Integer getMaxFanSpeed() {
        return maxFanSpeed;
    }

    public void setMaxFanSpeed(Integer maxFanSpeed) {
        this.maxFanSpeed = maxFanSpeed;
    }

    public Integer getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(Integer noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public CpuCooler() {
    }

    public CpuCooler(String name, Integer tdp, Integer maxFanSpeed, Integer noiseLevel) {
        this.name = name;
        this.tdp = tdp;
        this.maxFanSpeed = maxFanSpeed;
        this.noiseLevel = noiseLevel;
    }

    @Override
    public String toString() {
        return "CpuCooler [" +
                "name='" + name + '\'' +
                ", tdp=" + tdp +
                ", maxFanSpeed=" + maxFanSpeed +
                ", noiseLevel=" + noiseLevel +
                "]\n";
    }
}
