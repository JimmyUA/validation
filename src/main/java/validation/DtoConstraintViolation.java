package validation;

import java.util.Objects;

public class DtoConstraintViolation {

    private final String message;

    private DtoConstraintViolation(String message) {
        this.message = message;
    }

    public static DtoConstraintViolation from(String message){
        return new DtoConstraintViolation(message);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DtoConstraintViolation that = (DtoConstraintViolation) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
