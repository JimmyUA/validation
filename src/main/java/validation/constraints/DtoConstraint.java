package validation.constraints;

import validation.validotors.DtoFieldValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface DtoConstraint {
    Class<? extends DtoFieldValidator<?, ?>>[] validatedBy();
}
