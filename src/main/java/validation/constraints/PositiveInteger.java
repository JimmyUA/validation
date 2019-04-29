package validation.constraints;


import validation.validators.PositiveIntegerValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@DtoConstraint(validatedBy = PositiveIntegerValidator.class)
public @interface PositiveInteger {
    String message() default "{javax.validation.constraints.PositiveIntegerValidator.message}";
}
