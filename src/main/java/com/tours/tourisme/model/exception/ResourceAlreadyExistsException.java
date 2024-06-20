package com.tours.tourisme.model.exception;

import static com.tours.tourisme.model.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class ResourceAlreadyExistsException extends ApiException{
    public ResourceAlreadyExistsException(String message) {super(CLIENT_EXCEPTION, message);}
}
