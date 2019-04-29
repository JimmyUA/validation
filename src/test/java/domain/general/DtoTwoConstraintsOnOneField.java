package domain.general;

import validation.constraints.NotNull;
import validation.constraints.StringLength;
import validation.domain.Dto;

public class DtoTwoConstraintsOnOneField implements Dto {

    @NotNull
    @StringLength(min = 6)
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
