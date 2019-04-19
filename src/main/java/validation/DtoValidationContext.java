package validation;

import validation.domain.Dto;

public class DtoValidationContext {
    private ValidateMetadata metadata;

    private DtoValidationContext (Dto dto){
        metadata = ValidateMetadata.of(dto);
    }

    public static DtoValidationContext of(Dto dto){
        return new DtoValidationContext(dto);
    }

    public boolean hasConstraints() {
        return metadata.hasConstraints();
    }
}
