package az.lesson32.service;

import az.lesson32.dto.TutorialDto;

import java.util.List;

public interface TutorialService {
    void save(TutorialDto tutorialDto);
    int update(TutorialDto book);
    TutorialDto findById(Long id);
    int deleteById(Long id);
    List<TutorialDto> getAllTutorial();
    List<TutorialDto> findByPublished(boolean published);
    List<TutorialDto> findByTitleContaining(String title);
    int deleteAll();
}
