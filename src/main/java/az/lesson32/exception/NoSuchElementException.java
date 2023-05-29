package az.lesson32.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class NoSuchElementException extends RuntimeException{
    private final HttpStatus httpStatus = HttpStatus.NO_CONTENT;
    public NoSuchElementException() {
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}
