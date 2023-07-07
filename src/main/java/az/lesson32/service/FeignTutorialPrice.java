package az.lesson32.service;

import az.lesson32.dto.PriceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "tutorial-price", url = "http://localhost:8081/price/api")
public interface FeignTutorialPrice {
    @GetMapping("/get-price/{id}")
    PriceDto getPrice(@PathVariable Long id);

}
