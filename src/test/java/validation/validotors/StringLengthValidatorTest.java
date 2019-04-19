package validation.validotors;

import domain.length.DtoWithStringLengthConstraint;
import domain.positive.DtoWithPositiveIntegerConstraint;
import domain.positive.DtoWithPositiveIntegerConstraintNotIntegerField;
import org.junit.Before;
import org.junit.Test;
import validation.domain.Dto;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class StringLengthValidatorTest {

    private StringLengthValidator validator;
    private Field fieldToValidate;
    private DtoWithStringLengthConstraint dtoStringLength;
    private String fourSymbolsString;

    @Before
    public void setUp() throws Exception {
        validator = new StringLengthValidator();
        dtoStringLength = new DtoWithStringLengthConstraint();
        fourSymbolsString = "four";
    }

    @Test
    public void falseIfNotInteger() throws Exception {
        DtoWithPositiveIntegerConstraint dtoToValidate = new DtoWithPositiveIntegerConstraint();
        fieldToValidate = getField("age", dtoToValidate);
        assertFalse(validator.isValid(dtoToValidate, fieldToValidate));
    }

    @Test
    public void trueInRangeForDefault() throws Exception {
        dtoStringLength.setDefaultMaxMin("four");
        fieldToValidate = getField("defaultMaxMin", dtoStringLength);
        assertTrue(validator.isValid(dtoStringLength, fieldToValidate));
    }

    @Test
    public void trueInRangeForMinTen() throws Exception {
        dtoStringLength.setMinTen("abcdefghijklmnopq");
        fieldToValidate = getField("minTen", dtoStringLength);
        assertTrue(validator.isValid(dtoStringLength, fieldToValidate));
    }

    @Test
    public void falseLessThanMinForMinTen() throws Exception {
        dtoStringLength.setMinTen("a");
        fieldToValidate = getField("minTen", dtoStringLength);
        assertFalse(validator.isValid(dtoStringLength, fieldToValidate));
    }

    @Test
    public void trueInRangeForMaxSix() throws Exception {
        dtoStringLength.setMaxSix("abc");
        fieldToValidate = getField("maxSix", dtoStringLength);
        assertTrue(validator.isValid(dtoStringLength, fieldToValidate));
    }

    @Test
    public void falseMoreThanMaxForMaxSix() throws Exception {
        dtoStringLength.setMaxSix("abcdefghijk");
        fieldToValidate = getField("maxSix", dtoStringLength);
        assertFalse(validator.isValid(dtoStringLength, fieldToValidate));
    }


    private Field getField(String name, Dto dto) throws NoSuchFieldException {
        return dto.getClass().getDeclaredField(name);
    }

}