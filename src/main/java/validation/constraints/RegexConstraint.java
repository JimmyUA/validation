package validation.constraints;


import validation.validators.RegexValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@DtoConstraint(validatedBy = RegexValidator.class)
public @interface RegexConstraint {
    String message() default "{javax.validation.constraints.StringLengthValidator.message}";

    String pattern();

}
