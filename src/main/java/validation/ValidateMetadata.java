package validation;

import validation.constraints.DtoConstraint;
import validation.domain.Dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateMetadata {
    private Map<Field, Set<Annotation>> fieldsWithConstraints;

    private ValidateMetadata(Dto dto){
        initMetadata(dto);
    }

    private void initMetadata(Dto dto) {
        Field[] fields = dto.getClass().getDeclaredFields();
        fieldsWithConstraints = Arrays.stream(fields)
                .collect(Collectors.
                        toMap(field -> field, field -> new HashSet<>(Arrays.asList(field.getAnnotations()))));
        fieldsWithConstraints.values().forEach(this::filterSetByDtoConstraints);
        fieldsWithConstraints.values().removeIf(Set::isEmpty);
    }

    private void filterSetByDtoConstraints(Set<Annotation> annotations) {
        annotations.removeIf(this::isNotDtoConstraint);
    }

    private boolean isNotDtoConstraint(Annotation annotation) {
        DtoConstraint dtoConstraintsAnnotation = annotation.annotationType().getAnnotation(DtoConstraint.class);
        return dtoConstraintsAnnotation == null;
    }

    public static ValidateMetadata of(Dto dto){
        return new ValidateMetadata(dto);
    }

    public boolean hasConstraints() {
        return !fieldsWithConstraints.isEmpty();
    }

    public Map<Field, Set<Annotation>> getFieldsWithConstraints() {
        return fieldsWithConstraints;
    }
}
