package az.lesson32.repository.impl;

import az.lesson32.dto.TutorialDto;
import az.lesson32.repository.TutorialRepository;
import az.lesson32.validator.Check;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TutorialRepositoryImpl implements TutorialRepository {

    private final JdbcTemplate jdbcTemplate;
    private final Check check;

    public TutorialRepositoryImpl(JdbcTemplate jdbcTemplate, Check checkDB) {
        this.jdbcTemplate = jdbcTemplate;
        this.check = checkDB;
    }

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
        String query = "INSERT INTO lesson32.tutorials(name,title,subject,published) values (?,?,?,?)";
        return jdbcTemplate.update(query, ps -> {
            ps.setString(1, tutorialDto.getName());
            ps.setString(2, tutorialDto.getTitle());
            ps.setString(3, tutorialDto.getSubject());
            ps.setBoolean(4, tutorialDto.isPublished());
        });
    }

    @Override
    public int update(TutorialDto tutorialDto) {
        String query = "UPDATE  lesson32.tutorials set name=? ,title=? , subject=? ,published=? where id=?";
        return jdbcTemplate.update(query, tutorialDto.getName(), tutorialDto.getTitle(), tutorialDto.getSubject(), tutorialDto.isPublished(), tutorialDto.getId());
    }

    @Override
    public TutorialDto findById(Long id) {
        String query = "SELECT * FROM lesson32.tutorials where id=?";
        Optional<TutorialDto> optionalTutorialDto = jdbcTemplate.query(query, mapper, id).stream().findFirst();
        return optionalTutorialDto.get();
    }

    @Override
    public int deleteById(Long id) {
        String query = "DELETE FROM lesson32.tutorials WHERE id=?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public List<TutorialDto> getAllTutorial() {
        String query = "SELECT * FROM lesson32.tutorials";
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public List<TutorialDto> findByPublished(boolean published) {
        String query = "SELECT * FROM lesson32.tutorials where published=?";
        return jdbcTemplate.query(query, mapper, published);
    }

    @Override
    public List<TutorialDto> findByTitleContaining(String title) {
        String query = "SELECT * FROM lesson32.tutorials WHERE title LIKE ?";
        String wildcardTitle = "%" + title + "%";
        return jdbcTemplate.query(query, mapper, wildcardTitle);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM lesson32.tutorials");
    }
}
