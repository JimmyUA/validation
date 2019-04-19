package domain.positive;

import validation.constraints.NotNull;
import validation.domain.Dto;

public class DtoWithPositiveIntegerConstraintNotIntegerField implements Dto {

    @NotNull
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
