package validation.constraints;


import validation.validators.StringLengthValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@DtoConstraint(validatedBy = StringLengthValidator.class)
public @interface StringLength {
    String message() default "{javax.validation.constraints.StringLengthValidator.message}";

    int min() default 0;
    int max() default Integer.MAX_VALUE;
}
