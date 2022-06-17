package com.example.pcstoresystemsupport.model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Motherboard implements CaseComponent {


    @Override
    public Attribute getIdAttribute() {
        return null;
    }
    private String name;
    private String producer;
    private Integer ramSlotCapacity;
    private Integer maxInternalMemory;
    private Integer numOfMemorySlots;
    private String ramSlotType;
    private String socket;
    private String gpuSlot;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getRamSlotCapacity() {
        return ramSlotCapacity;
    }

    public void setRamSlotCapacity(Integer ramSlotCapacity) {
        this.ramSlotCapacity = ramSlotCapacity;
    }

    public Integer getMaxInternalMemory() {
        return maxInternalMemory;
    }

    public void setMaxInternalMemory(Integer maxInternalMemory) {
        this.maxInternalMemory = maxInternalMemory;
    }

    public Integer getNumOfMemorySlots() {
        return numOfMemorySlots;
    }

    public void setNumOfMemorySlots(Integer numOfMemorySlots) {
        this.numOfMemorySlots = numOfMemorySlots;
    }

    public String getRamSlotType() {
        return ramSlotType;
    }

    public void setRamSlotType(String ramSlotType) {
        this.ramSlotType = ramSlotType;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getGpuSlot() {
        return gpuSlot;
    }

    public void setGpuSlot(String gpuSlot) {
        this.gpuSlot = gpuSlot;
    }

    public Motherboard() {
    }

    public Motherboard(String name, String producer, Integer ramSlotCapacity, Integer maxInternalMemory, Integer numOfMemorySlots, String ramSlotType, String socket, String gpuSlot) {
        this.name = name;
        this.producer = producer;
        this.ramSlotCapacity = ramSlotCapacity;
        this.maxInternalMemory = maxInternalMemory;
        this.numOfMemorySlots = numOfMemorySlots;
        this.ramSlotType = ramSlotType;
        this.socket = socket;
        this.gpuSlot = gpuSlot;
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", ramSlotCapacity=" + ramSlotCapacity +
                ", maxInternalMemory=" + maxInternalMemory +
                ", numOfMemorySlots=" + numOfMemorySlots +
                ", ramSlotType='" + ramSlotType + '\'' +
                ", socket='" + socket + '\'' +
                ", gpuSlot='" + gpuSlot + '\'' +
                '}';
    }
}
