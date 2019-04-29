package validation.constraints;

import validation.domain.Dto;
import validation.validators.DtoFieldValidator;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface DtoConstraint {
    Class<? extends DtoFieldValidator<? extends Annotation, ? extends Dto>> validatedBy();
}
