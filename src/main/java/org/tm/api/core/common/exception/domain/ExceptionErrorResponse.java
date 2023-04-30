package org.tm.api.core.common.exception.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionErrorResponse {
    private String type;
    private String reason;
}

