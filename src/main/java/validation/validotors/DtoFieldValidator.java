package validation.validotors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface DtoFieldValidator<A extends Annotation, T> {

    default void initialize(A constraintAnnotation) {
    }

    boolean isValid(T value, Field field);

    default Object getFieldValue(T value, Field field) {
        try {
            field.setAccessible(true);
            return field.get(value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can not get field value", e);
        }
    }
}
