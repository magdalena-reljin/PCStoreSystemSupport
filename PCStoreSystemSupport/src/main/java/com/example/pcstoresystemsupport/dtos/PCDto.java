package com.example.pcstoresystemsupport.dtos;

import com.example.pcstoresystemsupport.model.*;

public class PCDto {
    private MotherboardDto motherboard;
    private ProcessorDto processor;
    private GpuDto gpu;
    private CpuCoolerDto cooler;
    private RamDto ram;

    public MotherboardDto getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(MotherboardDto motherboard) {
        this.motherboard = motherboard;
    }

    public ProcessorDto getProcessor() {
        return processor;
    }

    public void setProcessor(ProcessorDto processor) {
        this.processor = processor;
    }

    public GpuDto getGpu() {
        return gpu;
    }

    public void setGpu(GpuDto gpu) {
        this.gpu = gpu;
    }

    public CpuCoolerDto getCooler() {
        return cooler;
    }

    public void setCooler(CpuCoolerDto cooler) {
        this.cooler = cooler;
    }

    public RamDto getRam() {
        return ram;
    }

    public void setRam(RamDto ram) {
        this.ram = ram;
    }

    public PCDto(MotherboardDto motherboard, ProcessorDto processor, GpuDto gpu, CpuCoolerDto cooler, RamDto ram) {
        this.motherboard = motherboard;
        this.processor = processor;
        this.gpu = gpu;
        this.cooler = cooler;
        this.ram = ram;
    }

    public PCDto() {
    }

    @Override
    public String toString() {
        return "PCDto{" +
                "motherboard=" + motherboard +
                ", processor=" + processor +
                ", gpu=" + gpu +
                ", cooler=" + cooler +
                ", ram=" + ram +
                '}';
    }
}
