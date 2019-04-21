package validation.validators;

import validation.constraints.NotNull;
import validation.domain.Dto;

import java.lang.reflect.Field;

public class NotNullValidator implements DtoFieldValidator<NotNull, Dto> {

    private transient boolean isLocked = false;

    @Override
    public boolean isValid(Dto value, Field field) {
        Object fieldValue = getFieldValue(value, field);
        return fieldValue != null;
    }

    @Override
    public void lock() {
        isLocked = true;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public void unLock() {
        isLocked = false;
    }
}
