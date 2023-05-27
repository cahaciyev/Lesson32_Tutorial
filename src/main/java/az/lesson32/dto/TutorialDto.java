package az.lesson32.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class TutorialDto {
    private int id;
    private String title;
    private String name;
    private String subject;
    private boolean published;
}
