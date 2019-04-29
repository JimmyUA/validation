package validation.validators;

import domain.DtoWithNotNullConstraint;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotNullValidatorTest {

    private NotNullValidator validator;
    private DtoWithNotNullConstraint dtoToValidate;
    private Field fieldToValidate;

    @Before
    public void setUp() throws Exception {
        validator = new NotNullValidator();
        dtoToValidate = new DtoWithNotNullConstraint();
    }

    @Test
    public void isValidWhenNotNull() throws Exception {
        dtoToValidate.setName("Bob");
        fieldToValidate = getNameField();
        assertTrue(validator.isValid(dtoToValidate, fieldToValidate));
    }

    @Test
    public void isNotValidWhenNull() throws Exception {
        dtoToValidate.setName(null);
        fieldToValidate = getNameField();
        assertFalse(validator.isValid(dtoToValidate, fieldToValidate));
    }

    private Field getNameField() throws NoSuchFieldException {
        return dtoToValidate.getClass().getDeclaredField("name");
    }
}