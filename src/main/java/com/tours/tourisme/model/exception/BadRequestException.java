package com.tours.tourisme.model.exception;

import static com.tours.tourisme.model.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class BadRequestException extends ApiException {
    public BadRequestException(String message){
        super(CLIENT_EXCEPTION,message);
    }
}
