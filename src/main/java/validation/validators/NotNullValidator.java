package validation.validators;

import validation.constraints.NotNull;
import validation.domain.Dto;

import java.lang.reflect.Field;

public class NotNullValidator implements DtoFieldValidator<NotNull, Dto> {

    @Override
    public boolean isValid(Dto value, Field field) {
        Object fieldValue = getFieldValue(value, field);
        return fieldValue != null;
    }
}
