package org.tm.api.core.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.tm.api.core.common.exception.domain.GenericError;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final GenericError error;
    private final HttpStatus status;
}
