package az.lesson32.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class ExceptionModel {
    private HttpStatus errorStatus;
    private LocalDateTime errorTime;
    private String errorMessage;
    private int errorCode;
}
