package az.lesson32.repository;

import az.lesson32.dto.TutorialDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TutorialRepository
{
    int save(TutorialDto tutorialDto); //ok
    int update(TutorialDto book);  //ok
    TutorialDto findById(Long id);
    int deleteById(Long id);  //ok
    List<TutorialDto> findAll(); //ok
    List<TutorialDto> findByPublished(boolean published);  //ok
    List<TutorialDto> findByTitleContaining(String title); //ok
    int deleteAll(); //ok
}
