package validation.validotors;

import validation.DtoValidationContext;
import validation.domain.Dto;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Set;

public class GeneralValidator{

    public Set<ConstraintViolation<Dto>> validate(Dto value){

        DtoValidationContext validationContext = DtoValidationContext.of(value);
        if (!validationContext.hasConstraints()){
            return Collections.emptySet();
        }

        return null;
    }

}
