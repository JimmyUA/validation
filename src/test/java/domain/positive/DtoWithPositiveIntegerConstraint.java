package domain.positive;

import validation.constraints.PositiveInteger;
import validation.constraints.StringLength;
import validation.domain.Dto;

public class DtoWithPositiveIntegerConstraint implements Dto {

    @StringLength
    @PositiveInteger
    private Integer age;

    public void setAge(Integer age) {
        this.age = age;
    }
}
