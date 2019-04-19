package validation;

import domain.DtoWithNotNullConstraint;
import org.junit.Test;
import domain.NoAnnotationsDto;
import domain.NonConstraintsAnnotationsDto;

import static org.junit.Assert.*;

public class ValidateMetadataTest {

    @Test
    public void hasNoConstraints() {
        assertFalse(ValidateMetadata.of(new NoAnnotationsDto()).hasConstraints());
    }

    @Test
    public void hasNoConstraintsWhenAnotherAnnotationPresent() {
        assertFalse(ValidateMetadata.of(new NonConstraintsAnnotationsDto()).hasConstraints());
    }

    @Test
    public void hasConstraints() {
        assertTrue(ValidateMetadata.of(new DtoWithNotNullConstraint()).hasConstraints());
    }
}