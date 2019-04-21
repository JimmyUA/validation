package validation.validators;

import validation.constraints.RegexConstraint;
import validation.domain.Dto;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator implements DtoFieldValidator<RegexConstraint, Dto> {

    private transient boolean isLocked = false;
    private String regex;

    @Override
    public void initialize(RegexConstraint constraintAnnotation) {
        regex = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(Dto value, Field field) {
        initialize(field.getAnnotation(RegexConstraint.class));
        if (!field.getType().equals(String.class)){
            return false;
        }
        Object fieldValue = getFieldValue(value, field);
        if (fieldValue == null){
            return false;
        }
        String valueToValidate = (String) fieldValue;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valueToValidate);
        return matcher.matches();
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
