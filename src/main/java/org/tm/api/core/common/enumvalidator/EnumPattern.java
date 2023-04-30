package org.tm.api.core.common.enumvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@ReportAsSingleViolation
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message="This enum can't be")
@Constraint(validatedBy = EnumPatternValidator.class)
public @interface EnumPattern {
    Class<? extends Enum<?>> enumClazz();
    String message() default "Value is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
