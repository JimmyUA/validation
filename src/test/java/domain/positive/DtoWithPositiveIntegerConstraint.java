package domain.positive;

import validation.constraints.PositiveInteger;
import validation.constraints.RegexConstraint;
import validation.constraints.StringLength;
import validation.domain.Dto;

public class DtoWithPositiveIntegerConstraint implements Dto {

    @StringLength
    @PositiveInteger
    @RegexConstraint(pattern = "\\d")
    private Integer age;

    public void setAge(Integer age) {
        this.age = age;
    }
}
