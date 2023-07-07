package az.lesson32.controller;

import az.lesson32.dto.TutorialDto;
import az.lesson32.dto.TutorialWithPriceDto;
import az.lesson32.service.FeignTutorialPrice;
import az.lesson32.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ApiController {
    @Autowired
    TutorialService service;

    private final FeignTutorialPrice tutorialPrice;

    public ApiController(FeignTutorialPrice tutorialPrice) {
        this.tutorialPrice = tutorialPrice;
    }

    @GetMapping("/get-all-tutorial")
    public List<TutorialDto> getAllTutorial() {
        return service.getAllTutorial();
    }

    @GetMapping("/findbyid")
    public TutorialDto findbyid(@RequestParam Long id) {
        return service.findById(id);
    }

    @GetMapping("/findbypublished")
    public List<TutorialDto> findbypublished(@RequestParam Boolean published) {
        return service.findByPublished(published);
    }

    @PostMapping("/save")
    public void save(@RequestBody TutorialDto tutorialDto) {
        service.save(tutorialDto);
    }

    @DeleteMapping("/deletebyid")
    public String deleteById(@RequestParam Long id) {
        return (service.deleteById(id) == 1) ? "Successfully deleted" : "Error occurred";
    }

    @DeleteMapping("/deleteall")
    public int deleteAll() {
        return service.deleteAll();
    }

    @GetMapping("/findbytitle")
    public List<TutorialDto> findByTitleContaining(@RequestParam String title) {
        return service.findByTitleContaining(title);
    }

    @PatchMapping("/update")
    public int update(@RequestBody TutorialDto tutorialDto) {
        return service.update(tutorialDto);
    }

    @GetMapping("/get-tutorial-and-price/{id}")
    public ResponseEntity<TutorialWithPriceDto> getTutorialAndPrice(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getTutorialAndPrice(id));
    }
}
