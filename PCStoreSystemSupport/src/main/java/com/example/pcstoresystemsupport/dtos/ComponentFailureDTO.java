package com.example.pcstoresystemsupport.dtos;

public class ComponentFailureDTO {
    String failure;
    float percentage;

    public ComponentFailureDTO(String failure) {
        this.failure = failure;
    }
    public ComponentFailureDTO(String failure,float percentage) {
        this.failure = failure;
        this.percentage = percentage;
    }

    public ComponentFailureDTO() {
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
