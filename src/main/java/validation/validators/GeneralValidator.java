package validation.validators;

import validation.DtoConstraintViolation;
import validation.DtoValidationContext;
import validation.ValidateMetadata;
import validation.constraints.DtoConstraint;
import validation.domain.Dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralValidator {

    private Dto value;

    public Set<DtoConstraintViolation> validate(Dto value) {
        this.value = value;
        DtoValidationContext validationContext = DtoValidationContext.of(value);
        if (!validationContext.hasConstraints()) {
            return Collections.emptySet();
        }
        ValidateMetadata dtoMetadata = validationContext.getMetadata();
        Map<Field, Set<Annotation>> fieldsWithConstraints = dtoMetadata.getFieldsWithConstraints();
        Set<Set<DtoConstraintViolation>> violations = fieldsWithConstraints
                .entrySet().stream()
                .map(this::validateField)
                .collect(Collectors.toSet());
        return violations.stream().reduce((first, second) -> {
            first.addAll(second);
            return first;
        })
                .orElse(Collections.emptySet());
    }

    private Set<DtoConstraintViolation> validateField(Map.Entry<Field, Set<Annotation>> entry) {
        Set<DtoConstraintViolation> fieldViolations = new HashSet<>();
        Field field = entry.getKey();
        Set<Annotation> constraints = entry.getValue();
        constraints.forEach(constraint -> {
            if (!validateByConstraint(constraint, field, value)) {
                fieldViolations.add(DtoConstraintViolation.from(getConstraintDefaultMessage(constraint)));
            }
        });
        return fieldViolations;
    }

    private boolean validateByConstraint(Annotation constraint, Field field, Dto value) {
        DtoConstraint constraintType = constraint.annotationType().getAnnotation(DtoConstraint.class);
        Class<? extends DtoFieldValidator<? extends Annotation, ? extends Dto>> validatorClass = constraintType.validatedBy();
        Object validatorInstance = null;
        try {
            validatorInstance = validatorClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return false;
        }
        DtoFieldValidator validatorInstanceCasted = (DtoFieldValidator) validatorInstance;
        return validatorInstanceCasted.isValid(value, field);
    }

    private String getConstraintDefaultMessage(Annotation constraint) {
        Class<? extends Annotation> aClass = constraint.annotationType();
        Method messageMethod = null;
        try {
            messageMethod = aClass.getMethod("message");
        } catch (NoSuchMethodException e) {
            return "Validation error";
        }
        return (String) messageMethod.getDefaultValue();
    }

}
