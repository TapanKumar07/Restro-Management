package com.example.Hotel.Service.excepions.ResourceNotFound;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }
    public ResourceNotFoundException() {
        super("Unable to find record");
    }
}
