package org.tm.api.core.common.exception.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public class ValidationFieldError {

    @Schema(required = true)
    private final String field;

    @Schema(required = true)
    private final String message;

    public ValidationFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
