package az.lesson32.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tutorials", schema = "lesson32")
public class TutorialModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String title;
    String subject;
    Boolean published;
}
