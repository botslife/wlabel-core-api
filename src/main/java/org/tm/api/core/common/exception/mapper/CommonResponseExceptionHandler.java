package org.tm.api.core.common.exception.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.common.exception.GenericException;
import org.tm.api.core.common.exception.domain.ValidationError;
import org.tm.api.core.common.exception.domain.ValidationFieldError;

import java.util.*;

@ControllerAdvice
public class CommonResponseExceptionHandler{

    private final Logger logger = LoggerFactory.getLogger(CommonResponseExceptionHandler.class);

    @ExceptionHandler({GenericException.class})
    @ResponseBody
    ResponseEntity<Object> handleGenericException(GenericException exception) {
        return new ResponseEntity<>(exception.getError(), exception.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.info(exception.getMessage());
        ValidationError error = new ValidationError("REQUEST_INVALID", "check fields for more details.", getFields(exception));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private static List<ValidationFieldError> getFields(MethodArgumentNotValidException exception) {
        List<ValidationFieldError> fields = new ArrayList<>();
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for (ObjectError error : errors) {
            FieldError fieldError = (FieldError) error;
            fields.add(new ValidationFieldError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return fields;
    }

}
