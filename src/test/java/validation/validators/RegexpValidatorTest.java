package validation.validotors;

import domain.positive.DtoWithPositiveIntegerConstraint;
import domain.regexp.DtoWithReqexConstraint;
import org.junit.Before;
import org.junit.Test;
import validation.domain.Dto;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class RegexpValidatorTest {

    private RegexValidator validator;
    private Field fieldToValidate;
    private DtoWithReqexConstraint dtoRegexp;

    @Before
    public void setUp() {
        validator = new RegexValidator();
        dtoRegexp = new DtoWithReqexConstraint();
    }

    @Test
    public void falseIfNotString() throws Exception {
        DtoWithPositiveIntegerConstraint dtoToValidate = new DtoWithPositiveIntegerConstraint();
        fieldToValidate = getField("age", dtoToValidate);
        assertFalse(validator.isValid(dtoToValidate, fieldToValidate));
    }

    @Test
    public void falseIfNull() throws Exception {
        fieldToValidate = getField("digitsOnly", dtoRegexp);
        assertFalse(validator.isValid(dtoRegexp, fieldToValidate));
    }

    @Test
    public void falseIfContainsNotOnlyDigits() throws Exception {
        dtoRegexp.setDigitsOnly("312fff");
        fieldToValidate = getField("digitsOnly", dtoRegexp);
        assertFalse(validator.isValid(dtoRegexp, fieldToValidate));
    }

    @Test
    public void trueIfContainsOnlyDigits() throws Exception {
        dtoRegexp.setDigitsOnly("312");
        fieldToValidate = getField("digitsOnly", dtoRegexp);
        assertTrue(validator.isValid(dtoRegexp, fieldToValidate));
    }

    @Test
    public void trueIfContainsOnlyLowLatinLetters() throws Exception {
        dtoRegexp.setOnlyLatinLow("coin");
        fieldToValidate = getField("onlyLatinLow", dtoRegexp);
        assertTrue(validator.isValid(dtoRegexp, fieldToValidate));
    }

    @Test
    public void falseIfContainsShiftedLatinLetters() throws Exception {
        dtoRegexp.setOnlyLatinLow("Coin");
        fieldToValidate = getField("onlyLatinLow", dtoRegexp);
        assertFalse(validator.isValid(dtoRegexp, fieldToValidate));
    }

    private Field getField(String name, Dto dto) throws NoSuchFieldException {
        return dto.getClass().getDeclaredField(name);
    }
}