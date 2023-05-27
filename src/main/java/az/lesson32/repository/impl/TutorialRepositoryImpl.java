package az.lesson32.repository.impl;

import az.lesson32.dto.TutorialDto;
import az.lesson32.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TutorialRepositoryImpl implements TutorialRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    RowMapper<TutorialDto> mapper = (rs, rownumber) -> {
        TutorialDto tutorialDto = new TutorialDto();
        tutorialDto.setId(rs.getInt("id"));
        tutorialDto.setName(rs.getString("name"));
        tutorialDto.setTitle(rs.getString("title"));
        tutorialDto.setSubject(rs.getString("subject"));
        tutorialDto.setPublished(rs.getBoolean("published"));
        return tutorialDto;
    };

    @Override
    public int save(TutorialDto tutorialDto) {
        String query = "INSERT INTO lesson32.tutorial(name,title,subject,published) values (?,?,?,?)";
        return jdbcTemplate.update(query, ps -> {
            ps.setString(1, tutorialDto.getName());
            ps.setString(2, tutorialDto.getTitle());
            ps.setString(3, tutorialDto.getSubject());
            ps.setBoolean(4, tutorialDto.isPublished());
        });
    }

    @Override
    public int update(TutorialDto tutorialDto) {
        String query = "UPDATE  lesson32.tutorial set name=? ,title=? , subject=? ,published=? where id=?";
       return jdbcTemplate.update(query, tutorialDto.getName(), tutorialDto.getTitle(), tutorialDto.getSubject(), tutorialDto.isPublished(), tutorialDto.getId());
    }

    @Override
    public TutorialDto findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM lesson32.tutorial where id=? ", rs -> {
            TutorialDto tutorialDto = new TutorialDto();
            tutorialDto.setName(rs.getString("name"));
            tutorialDto.setTitle(rs.getString("title"));
            tutorialDto.setSubject(rs.getString("subject"));
            tutorialDto.setPublished(rs.getBoolean("published"));
            return tutorialDto;
        }, id);
    }

    @Override
    public int deleteById(Long id) {
        String query = "DELETE FROM lesson32.tutorial WHERE id=?";
        return jdbcTemplate.update(query, id );
    }

    @Override
    public List<TutorialDto> findAll() {
        String query = "SELECT * FROM lesson32.tutorial";
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public List<TutorialDto> findByPublished(boolean published) {
        String query = "SELECT * FROM lesson32.tutorial where published=?";
        return jdbcTemplate.query(query, mapper, published);
    }

    @Override
    public List<TutorialDto> findByTitleContaining(String title) {
        String query = "SELECT * FROM lesson32.tutorial WHERE title LIKE ?";
        String wildcardTitle = "%" + title + "%";
        return jdbcTemplate.query(query, mapper, wildcardTitle);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM lesson32.tutorial");
    }
}
