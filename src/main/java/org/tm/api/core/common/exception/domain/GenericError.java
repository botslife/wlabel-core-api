package org.tm.api.core.common.exception.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tm.api.core.common.exception.domain.constants.GenericExceptionType;


@AllArgsConstructor
@Getter
public class GenericError {
    @Schema(required = true)
    private final GenericExceptionType code;

    @Schema(required = true)
    private final String reason;

}
