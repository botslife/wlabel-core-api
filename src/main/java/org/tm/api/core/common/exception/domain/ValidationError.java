package org.tm.api.core.common.exception.domain;

import java.util.List;

public class ValidationError extends Error {

    private final List<ValidationFieldError> fields;

    public ValidationError(String code, String reason,
                           List<ValidationFieldError> fields) {
        super(code, reason);
        this.fields = fields;
    }

    public String getReason() {
        return super.getReason();
    }

    public List<ValidationFieldError> getFields() {
        return fields;
    }

}