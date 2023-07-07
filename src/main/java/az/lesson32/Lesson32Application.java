package az.lesson32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Lesson32Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson32Application.class, args);
    }
}
