package com.eindopdrachtbackend.dto;

public class DocumentDto {
    private String filename;
    private byte[] fileContent;  // meestal niet handig om te sturen, maar kan voor responses
    private Long customerId;

    // Constructors
    public DocumentDto() {}

    public DocumentDto(String filename, byte[] fileContent, Long customerId) {
        this.filename = filename;
        this.fileContent = fileContent;
        this.customerId = customerId;
    }

    // Getters en setters
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
