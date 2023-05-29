package az.lesson32.exception;

public class IDNotFoundException extends RuntimeException {
    public IDNotFoundException() {

    }
    public IDNotFoundException(String message) {
        super(message);
    }
}
