package az.lesson32.repository;

import az.lesson32.dto.TutorialDto;

import java.util.List;
public interface TutorialRepository
{
    int save(TutorialDto tutorialDto); //ok
    int update(TutorialDto book);  //ok
    TutorialDto findById(Long id);
    int deleteById(Long id);  //ok
    List<TutorialDto> getAllTutorial(); //ok
    List<TutorialDto> findByPublished(boolean published);  //ok
    List<TutorialDto> findByTitleContaining(String title); //ok
    int deleteAll(); //ok
}
