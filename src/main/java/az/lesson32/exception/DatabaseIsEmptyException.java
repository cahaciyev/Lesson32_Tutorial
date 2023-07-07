package az.lesson32.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DatabaseIsEmptyException extends RuntimeException {

   private final HttpStatus httpStatus = HttpStatus.NO_CONTENT;
    public DatabaseIsEmptyException(String message) {
        super(message);
    }

}
