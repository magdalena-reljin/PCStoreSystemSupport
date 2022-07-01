package com.example.pcstoresystemsupport.model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Ram implements CaseComponent {

    @Override
    public Attribute getIdAttribute() {
        return null;
    }
    private String name;
    private String producer;
    private Integer capacity;
    private Integer frequency;
    private String type;

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Ram(String name, String producer, Integer capacity, Integer frequency, String type) {
        this.name = name;
        this.producer = producer;
        this.capacity = capacity;
        this.frequency = frequency;
        this.type = type;
    }

    public Ram() {
    }

    @Override
    public String toString() {
        return "Ram [" +
                "name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", capacity=" + capacity +
                ", frequency=" + frequency +
                ", type='" + type + '\'' +
                "]\n";
    }
}
