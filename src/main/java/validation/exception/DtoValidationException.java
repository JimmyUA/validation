package validation.exception;

public class DtoValidationException extends RuntimeException{
    public DtoValidationException(String message) {
        super(message);
    }

    public DtoValidationException(String message, Exception e) {
        super(message, e);
    }
}
