package validation.validators;

import validation.constraints.PositiveInteger;
import validation.domain.Dto;

import java.lang.reflect.Field;

public class PositiveIntegerValidator implements DtoFieldValidator<PositiveInteger, Dto> {

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
}
