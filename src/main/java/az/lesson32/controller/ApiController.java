package az.lesson32.controller;

import az.lesson32.dto.TutorialDto;
import az.lesson32.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    TutorialService service;

    @GetMapping("/findall")
    public List<TutorialDto> getAllTutorial() {
        return service.findAll();
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
}
