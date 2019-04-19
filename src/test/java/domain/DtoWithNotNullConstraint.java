package domain;

import validation.constraints.NotNull;
import validation.domain.Dto;

public class DtoWithNotNullConstraint implements Dto {

    @NotNull
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
