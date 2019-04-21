package domain.general;

import validation.constraints.RegexConstraint;
import validation.constraints.StringLength;
import validation.domain.Dto;

public class DtoTwoStringConstraints implements Dto {

    @StringLength(min=42)
    @RegexConstraint(pattern = "^[1-9]*")
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
