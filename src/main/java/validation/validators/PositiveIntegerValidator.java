package validation.validators;

import validation.constraints.PositiveInteger;
import validation.domain.Dto;

import java.lang.reflect.Field;

public class PositiveIntegerValidator implements DtoFieldValidator<PositiveInteger, Dto> {

    private transient boolean isLocked = false;

    @Override
    public boolean isValid(Dto value, Field field) {
        Object fieldValue = getFieldValue(value, field);
        if (field.getType().equals(Integer.class)) {
            if(fieldValue == null){
                return true;
            }
            Integer valueToCompare = (Integer) fieldValue;
            return valueToCompare >= 0;
        }

        return false;
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
