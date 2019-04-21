package validation.validators;

import validation.domain.Dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface DtoFieldValidator<A extends Annotation, T extends Dto> {

    default void initialize(A constraintAnnotation) {
    }

    boolean isValid(T value, Field field);

    default Object getFieldValue(Dto value, Field field) {
        try {
            field.setAccessible(true);
            return field.get(value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can not get field value", e);
        }
    }

    void lock();

    boolean isLocked();

    void unLock();
}
