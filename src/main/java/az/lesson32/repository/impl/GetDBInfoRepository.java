package az.lesson32.repository.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class GetDBInfoRepository {
    private final JdbcTemplate template;

    public GetDBInfoRepository(JdbcTemplate template) {
        this.template = template;
    }

    public int getRowCount() {
        return template.queryForObject("select count(*) from lesson32.tutorial", Integer.class);
    }
}
