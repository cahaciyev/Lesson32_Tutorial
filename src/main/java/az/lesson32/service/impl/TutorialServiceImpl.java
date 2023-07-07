package az.lesson32.service.impl;

import az.lesson32.dto.PriceDto;
import az.lesson32.dto.TutorialDto;
import az.lesson32.dto.TutorialWithPriceDto;
import az.lesson32.repository.TutorialRepository;
import az.lesson32.service.FeignTutorialPrice;
import az.lesson32.service.TutorialService;
import az.lesson32.validator.Check;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TutorialServiceImpl implements TutorialService {

    final
    TutorialRepository repository;
    final
    Check check;
    final FeignTutorialPrice feignTutorialPrice;


    public TutorialServiceImpl(Check check, TutorialRepository repository, FeignTutorialPrice feignTutorialPrice) {
        this.check = check;
        this.repository = repository;
        this.feignTutorialPrice = feignTutorialPrice;
    }

    @Override
    public void save(TutorialDto tutorialDto) {
        if (repository.save(tutorialDto) == 1) {
            log.info("Successfully saved");
        } else {
            log.error("Error occurred");
        }
    }

    @Override
    public int update(TutorialDto tutorialDto) {
        check.idChecker((long) tutorialDto.getId());
        log.info("Update method starter");
        return repository.update(tutorialDto);
    }

    @Override
    public TutorialDto findById(Long id) {
        check.idChecker(id);
        log.info("Added id:" + id);
        return repository.findById(id);
    }

    @Override
    public int deleteById(Long id) {
        check.idChecker(id);
        log.info("ID number " + id + " has been deleted successfully");
        return repository.deleteById(id);
    }

    @Override
    public List<TutorialDto> getAllTutorial() {
        check.isDataBaseEmpty();
        return repository.getAllTutorial();
    }

    @Override
    public List<TutorialDto> findByPublished(boolean published) {
        check.isDataBaseEmpty();
        return repository.findByPublished(published);
    }

    @Override
    public List<TutorialDto> findByTitleContaining(String title) {
        check.elementChecker(title);
        return repository.findByTitleContaining(title);

    }

    @Override
    public int deleteAll() {
        check.isDataBaseEmpty();
        return repository.deleteAll();
    }

    public TutorialWithPriceDto getTutorialAndPrice(Long id) {
        TutorialDto tutorialDto = repository.findById(id);
        PriceDto priceDto = feignTutorialPrice.getPrice(id);
        return
                TutorialWithPriceDto.builder()
                        .name(tutorialDto.getName())
                        .title(tutorialDto.getTitle())
                        .published(tutorialDto.isPublished())
                        .subject(tutorialDto.getSubject())
                        .price(priceDto.getPrice()).build();
    }
}
