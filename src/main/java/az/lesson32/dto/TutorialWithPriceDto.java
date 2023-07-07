package az.lesson32.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class TutorialWithPriceDto {
    private String title;
    private String name;
    private String subject;
    private boolean published;
    private double price;
}
