package validation.validators;

import validation.constraints.StringLength;
import validation.domain.Dto;

import java.lang.reflect.Field;

public class StringLengthValidator implements DtoFieldValidator<StringLength, Dto> {

    private int min;
    private int max;
    private transient boolean isLocked = false;

    @Override
    public void initialize(StringLength constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Dto value, Field field) {
        initialize(field.getAnnotation(StringLength.class));
        if (!field.getType().equals(String.class)){
            return false;
        }
        Object fieldValue = getFieldValue(value, field);
        if (fieldValue == null){
            return false;
        }
        String stringToValidate = (String) fieldValue;
        int stringLength = stringToValidate.length();

        return stringLength < max && stringLength > min;
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
