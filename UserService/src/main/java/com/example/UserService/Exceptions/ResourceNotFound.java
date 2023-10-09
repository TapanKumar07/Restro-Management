package com.example.UserService.Exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound() {
        super("Resource is Not Found!");
    }
    public ResourceNotFound(String message) {
        super(message);
    }

}
