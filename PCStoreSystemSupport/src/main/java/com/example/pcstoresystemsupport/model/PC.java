package com.example.pcstoresystemsupport.model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class PC implements CaseComponent {
    private Motherboard motherboard;
    private Processor processor;
    private Gpu gpu;
    private CpuCooler cooler;
    private Ram ram;
    @Override
    public Attribute getIdAttribute() {
        return null;
    }

    public PC(Motherboard motherboard, Processor processor, Gpu gpu, CpuCooler cooler, Ram ram) {
        this.motherboard = motherboard;
        this.processor = processor;
        this.gpu = gpu;
        this.cooler = cooler;
        this.ram = ram;
    }

    public PC() {}

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    public CpuCooler getCooler() {
        return cooler;
    }

    public void setCooler(CpuCooler cooler) {
        this.cooler = cooler;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "PC{" +
                "motherboard=" + motherboard +
                ", processor=" + processor +
                ", gpu=" + gpu +
                ", cooler=" + cooler +
                ", ram=" + ram +
                '}';
    }
}
