package com.eindopdrachtbackend.dto;

public class StockDto {
    private String partName;
    private String description;
    private Double price;

    public StockDto() {}

    public StockDto(String partName, String description, Double price) {
        this.partName = partName;
        this.description = description;
        this.price = price;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
