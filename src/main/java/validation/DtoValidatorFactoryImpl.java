package validation;

import validation.constraints.DtoConstraint;
import validation.domain.Dto;
import validation.validators.DtoFieldValidator;

import java.lang.annotation.Annotation;

import static validation.validators.validatorspool.ValidatorsPool.POOL;

public enum  DtoValidatorFactoryImpl implements DtoValidatorFactory {

    SIMPLE_FACTORY;

    @Override
    public DtoFieldValidator<? extends Annotation, ? extends Dto> getValidatorInstance(Annotation constraint) {
        DtoConstraint constraintType = constraint.annotationType().getAnnotation(DtoConstraint.class);
        Class<? extends DtoFieldValidator<? extends Annotation, ? extends Dto>> validatorClass =
                constraintType.validatedBy();
        return POOL.getValidator(validatorClass);
    }
}
