package com.example.pcstoresystemsupport.dtos;

public class GpuDto {
    private String name;
    private Integer memorySize;
    private String memoryType;
    private double minPSU;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public double getMinPSU() {
        return minPSU;
    }

    public void setMinPSU(double minPSU) {
        this.minPSU = minPSU;
    }

    public GpuDto(String name, Integer memorySize, String memoryType, double minPSU) {
        this.name = name;
        this.memorySize = memorySize;
        this.memoryType = memoryType;
        this.minPSU = minPSU;
    }

    public GpuDto() {
    }

    @Override
    public String toString() {
        return "GpuDto{" +
                "name='" + name + '\'' +
                ", memorySize=" + memorySize +
                ", memoryType='" + memoryType + '\'' +
                ", minPSU=" + minPSU +
                '}';
    }
}
