package validation;

import validation.domain.Dto;
import validation.validators.DtoFieldValidator;

import java.lang.annotation.Annotation;

public interface DtoValidatorFactory {

    DtoFieldValidator<? extends Annotation, ? extends Dto> getValidatorInstance(Annotation constraint);
}
