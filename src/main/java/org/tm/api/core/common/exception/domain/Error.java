package org.tm.api.core.common.exception.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public class Error {

    @Schema(required = true)
    private final String code;

    @Schema(required = true)
    private final String reason;

    public Error(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

}
