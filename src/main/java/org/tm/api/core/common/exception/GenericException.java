package org.tm.api.core.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.tm.api.core.common.exception.domain.GenericError;

@Getter
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenericException extends RuntimeException {
    private  HttpStatus status;
    private  GenericError error;
    public GenericException(){
        super();
    }
    public GenericException(HttpStatus status, GenericError error){
        this.status = status;
        this.error = error;
    }
    public GenericException(String message,Throwable cause){
        super(message,cause);
    }
    public GenericException(String message){
        super(message);
    }
    public GenericException(Throwable cause){
        super(cause);
    }
}
