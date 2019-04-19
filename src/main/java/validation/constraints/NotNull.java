package validation.constraints;


import validation.validotors.NotNullValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@DtoConstraint(validatedBy = {NotNullValidator.class})
public @interface NotNull {
    String message() default "{javax.validation.constraints.NotNull.message}";
}
