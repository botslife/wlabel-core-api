package org.tm.api.core.common.exception.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tm.api.core.common.exception.domain.constants.BusinessExceptionType;

@AllArgsConstructor
@Getter
public class BusinessError {

    @Schema(required = true)
    private final BusinessExceptionType code;

    @Schema(required = true)
    private final String reason;
}
