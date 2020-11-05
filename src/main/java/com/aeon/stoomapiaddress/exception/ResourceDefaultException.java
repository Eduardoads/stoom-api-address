package com.aeon.stoomapiaddress.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class ResourceDefaultException extends RuntimeException{
    public ResourceDefaultException(String msg){
        super(msg);
    }
}
