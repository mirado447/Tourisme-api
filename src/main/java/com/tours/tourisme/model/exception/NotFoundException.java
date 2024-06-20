package com.tours.tourisme.model.exception;

import static com.tours.tourisme.model.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class NotFoundException extends ApiException{
    public NotFoundException(String message){super(CLIENT_EXCEPTION, message);}
}
