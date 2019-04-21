package validation.validators;

import domain.DtoWithNotNullConstraint;
import domain.NonConstraintsAnnotationsDto;
import domain.general.DtoTwoConstraintsOnOneField;
import org.junit.Before;
import org.junit.Test;
import validation.DtoConstraintViolation;
import validation.constraints.NotNull;
import validation.constraints.StringLength;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneralValidatorTest {

    private GeneralValidator validator;

    @Before
    public void setUp() {
        validator = new GeneralValidator();
    }

    @Test
    public void noViolationsWhenNoConstraints() {
        Set<DtoConstraintViolation> violations = validator
                .validate(new NonConstraintsAnnotationsDto());

        assertTrue(violations.isEmpty());
    }

    @Test
    public void notNullViolationWhenConstrainedFieldIsNull() throws NoSuchMethodException {
        Set<DtoConstraintViolation> violations = validator
                .validate(new DtoWithNotNullConstraint());

        assertEquals(1, violations.size());
        assertTrue(violations.contains(DtoConstraintViolation.from((String) NotNull.class.getMethod("message").getDefaultValue())));
    }

    @Test
    public void notLengthViolationWhenConstrainedFieldIsTooLongTwoConstraints() throws NoSuchMethodException {
        DtoTwoConstraintsOnOneField dtoTwoConstraintsOnOneField = new DtoTwoConstraintsOnOneField();
        dtoTwoConstraintsOnOneField.setName("Low");
        Set<DtoConstraintViolation> violations = validator
                .validate(dtoTwoConstraintsOnOneField);

        assertEquals(1, violations.size());
        assertTrue(violations.contains(DtoConstraintViolation.from((String) StringLength.class.getMethod("message").getDefaultValue())));
    }

    
}