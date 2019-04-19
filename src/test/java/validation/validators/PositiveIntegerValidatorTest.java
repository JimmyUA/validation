package validation.validotors;

import domain.positive.DtoWithPositiveIntegerConstraint;
import domain.positive.DtoWithPositiveIntegerConstraintNotIntegerField;
import org.junit.Before;
import org.junit.Test;
import validation.domain.Dto;

import java.lang.reflect.Field;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositiveIntegerValidatorTest {


    private PositiveIntegerValidator validator;
    private Field fieldToValidate;

    @Before
    public void setUp() throws Exception {
        validator = new PositiveIntegerValidator();
    }

    @Test
    public void falseIfNotInteger() throws Exception {
        DtoWithPositiveIntegerConstraintNotIntegerField dtoToValidate = new DtoWithPositiveIntegerConstraintNotIntegerField();
        dtoToValidate.setName("Bob");
        fieldToValidate = getField("name",dtoToValidate);
        assertFalse(validator.isValid(dtoToValidate, fieldToValidate));
    }

    @Test
    public void trueIfNullInteger() throws Exception {
        DtoWithPositiveIntegerConstraint dtoToValidate = new DtoWithPositiveIntegerConstraint();
        fieldToValidate = getField("age",dtoToValidate);
        assertTrue(validator.isValid(dtoToValidate, fieldToValidate));
    }

    @Test
    public void trueIfMoreThanZero() throws Exception {
        DtoWithPositiveIntegerConstraint dtoToValidate = new DtoWithPositiveIntegerConstraint();
        dtoToValidate.setAge(42);
        fieldToValidate = getField("age",dtoToValidate);
        assertTrue(validator.isValid(dtoToValidate, fieldToValidate));
    }

    @Test
    public void trueIfZero() throws Exception {
        DtoWithPositiveIntegerConstraint dtoToValidate = new DtoWithPositiveIntegerConstraint();
        dtoToValidate.setAge(0);
        fieldToValidate = getField("age",dtoToValidate);
        assertTrue(validator.isValid(dtoToValidate, fieldToValidate));
    }

    @Test
    public void falseIfLessThanZero() throws Exception {
        DtoWithPositiveIntegerConstraint dtoToValidate = new DtoWithPositiveIntegerConstraint();
        dtoToValidate.setAge(-236524);
        fieldToValidate = getField("age",dtoToValidate);
        assertFalse(validator.isValid(dtoToValidate, fieldToValidate));
    }

    private Field getField(String name, Dto dto) throws NoSuchFieldException {
        return dto.getClass().getDeclaredField(name);
    }
}