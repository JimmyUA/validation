package validation;

import java.util.Objects;

public class DtoConstraintViolation {

    private final String message;
    private final String field;

    private DtoConstraintViolation(String message, String field) {

        this.message = message;
        this.field = field;
    }

    public static DtoConstraintViolation from(String message, String field){
        return new DtoConstraintViolation(message, field);
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DtoConstraintViolation that = (DtoConstraintViolation) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, field);
    }
}
