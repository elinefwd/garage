package com.eindopdrachtbackend.dto;

public class InspectionDto {
    private String action;
    private String date;
    private Long vehicleId; // Dit is de ID van het voertuig

    // Constructor
    public InspectionDto() {}

    public InspectionDto(String action, String date, Long vehicleId) {
        this.action = action;
        this.date = date;
        this.vehicleId = vehicleId;
    }

    // Getters & setters
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
