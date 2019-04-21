package validation.validators;

import domain.DtoWithNotNullConstraint;
import domain.NonConstraintsAnnotationsDto;
import domain.general.DtoTwoConstraintsOnOneField;
import domain.general.DtoTwoInvalidFields;
import domain.general.DtoTwoStringConstraints;
import org.junit.Before;
import org.junit.Test;
import validation.DtoConstraintViolation;
import validation.constraints.NotNull;
import validation.constraints.StringLength;

import java.lang.annotation.Annotation;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneralValidatorTest {

    private GeneralValidator validator;
    private final String NAME_FIELD = "name";
    private final String AGE_FIELD = "age";

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
        assertTrue(violations.contains(DtoConstraintViolation.from(getDefaultMessage(NotNull.class), NAME_FIELD)));
    }

    @Test
    public void notLengthViolationWhenConstrainedFieldIsTooLongTwoConstraints() throws NoSuchMethodException {
        DtoTwoConstraintsOnOneField dtoTwoConstraintsOnOneField = new DtoTwoConstraintsOnOneField();
        dtoTwoConstraintsOnOneField.setName("Low");
        Set<DtoConstraintViolation> violations = validator
                .validate(dtoTwoConstraintsOnOneField);

        assertEquals(1, violations.size());
        assertTrue(violations.contains(DtoConstraintViolation.from(getDefaultMessage(StringLength.class), NAME_FIELD)));
    }

    @Test
    public void twoViolationsOnTwoConstraintsAreViolated() throws NoSuchMethodException {
        DtoTwoStringConstraints dtoTwoStringConstraints = new DtoTwoStringConstraints();
        dtoTwoStringConstraints.setName("Low");
        Set<DtoConstraintViolation> violations = validator
                .validate(dtoTwoStringConstraints);

        assertEquals(2, violations.size());
        assertTrue(violations.contains(DtoConstraintViolation.from((String) StringLength.class.getMethod("message").getDefaultValue(), NAME_FIELD)));
    }

    @Test
    public void twoViolationsOnTwoInvalidFieldsSameConstraint() throws NoSuchMethodException {
        DtoTwoInvalidFields dtoTwoInvalidFields = new DtoTwoInvalidFields();
        Set<DtoConstraintViolation> violations = validator
                .validate(dtoTwoInvalidFields);

        assertEquals(2, violations.size());
        assertTrue(violations.contains(DtoConstraintViolation.from(getDefaultMessage(NotNull.class), NAME_FIELD)));
        assertTrue(violations.contains(DtoConstraintViolation.from(getDefaultMessage(NotNull.class), AGE_FIELD)));
    }

    private String getDefaultMessage(Class<? extends Annotation> type) throws NoSuchMethodException {
        return (String) type.getMethod("message").getDefaultValue();
    }

}