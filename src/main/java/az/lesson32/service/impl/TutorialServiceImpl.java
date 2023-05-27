package az.lesson32.service.impl;

import az.lesson32.dto.TutorialDto;
import az.lesson32.repository.TutorialRepository;
import az.lesson32.service.TutorialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TutorialServiceImpl implements TutorialService {

    @Qualifier("tutorialRepositoryImpl")
    @Autowired
    TutorialRepository repository;

    @Override
    public void save(TutorialDto tutorialDto) {
        if (repository.save(tutorialDto) == 1) {
            log.info("Successfully saved");
        } else log.error("Error occurred");
    }

    @Override
    public int update(TutorialDto tutorialDto) {
        log.info("Update method starter");
        return repository.update(tutorialDto);
    }

    @Override
    public TutorialDto findById(Long id) {
        log.info("Added id:" + id);
        return repository.findById(id);
    }

    @Override
    public int deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public List<TutorialDto> findAll() {
        return repository.findAll();
    }

    @Override
    public List<TutorialDto> findByPublished(boolean published) {
        return repository.findByPublished(published);
    }

    @Override
    public List<TutorialDto> findByTitleContaining(String title) {
        return repository.findByTitleContaining(title);

    }

    @Override
    public int deleteAll() {
        return repository.deleteAll();
    }

}
