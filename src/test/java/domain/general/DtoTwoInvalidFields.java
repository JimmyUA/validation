package domain.general;

import validation.constraints.NotNull;
import validation.domain.Dto;

public class DtoTwoInvalidFields implements Dto {

    @NotNull
    private String name;

    @NotNull
    private Integer age;
}
