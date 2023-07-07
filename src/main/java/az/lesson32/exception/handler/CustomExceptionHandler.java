package az.lesson32.exception.handler;

import az.lesson32.exception.DatabaseIsEmptyException;
import az.lesson32.exception.IDNotFoundException;
import az.lesson32.exception.InvalidBodyException;
import az.lesson32.exception.NoSuchElementException;
import az.lesson32.model.ExceptionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    final
    ExceptionModel exceptionModel;

    public CustomExceptionHandler(ExceptionModel exceptionModel) {
        this.exceptionModel = exceptionModel;
    }

    @ExceptionHandler(IDNotFoundException.class)
    public ResponseEntity<?> handleException(IDNotFoundException exception) {
        log.error(exception.getMessage());
        exceptionModel.setErrorCode(HttpStatus.NOT_FOUND.value());
        exceptionModel.setErrorTime(LocalDateTime.now());
        exceptionModel.setErrorMessage(exception.getMessage());
        exceptionModel.setErrorStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionModel);
    }

    @ExceptionHandler(InvalidBodyException.class)
    public ResponseEntity<ExceptionModel> handleException(InvalidBodyException ex) {
        log.error(ex.getMessage());
        exceptionModel.setErrorStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setErrorMessage(ex.getMessage());
        exceptionModel.setErrorTime(LocalDateTime.now());
        exceptionModel.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(exceptionModel);
    }

    @ExceptionHandler(DatabaseIsEmptyException.class)
    public ResponseEntity<ExceptionModel> handleException(DatabaseIsEmptyException ex) {
        exceptionModel.setErrorCode(ex.getHttpStatus().value());
        exceptionModel.setErrorTime(LocalDateTime.now());
        exceptionModel.setErrorStatus(ex.getHttpStatus());
        exceptionModel.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(exceptionModel);
    }


}
