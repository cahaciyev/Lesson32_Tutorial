package az.lesson32.validator;

import az.lesson32.exception.DatabaseIsEmptyException;
import az.lesson32.exception.IDNotFoundException;
import az.lesson32.exception.NoSuchElementException;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Data
public class Check {

    private final JdbcTemplate template;

    public Check(JdbcTemplate template) {
        this.template = template;
    }
    public void idChecker(Long id) {
        Boolean ifExists = template.queryForObject("SELECT EXISTS (SELECT 1 FROM lesson32.tutorials WHERE id = ?)", Boolean.class, id);
        if (Boolean.FALSE.equals(ifExists)) {
            throw new IDNotFoundException("ID number " + id + " not found");
        }
    }

    public void isDataBaseEmpty() {
        Boolean isEmpty = template.queryForObject("SELECT EXISTS (SELECT 1 FROM lesson32.tutorials)", Boolean.class);
        if (Boolean.FALSE.equals(isEmpty)) {
            throw new DatabaseIsEmptyException("Database is empty");
        }
    }

    public void elementChecker(String element) {
        Boolean ifExists = template.queryForObject("SELECT EXISTS (SELECT 1 FROM lesson32.tutorials WHERE title = ?)", Boolean.class, element);
        if (Boolean.FALSE.equals(ifExists)) {
            throw new NoSuchElementException(element + " not found in DB");
        }
    }
}

