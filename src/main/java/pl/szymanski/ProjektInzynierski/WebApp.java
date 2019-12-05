package pl.szymanski.ProjektInzynierski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.szymanski.ProjektInzynierski.repository.TemperatureReadingRepo;

@SpringBootApplication
public class WebApp {

    public static void main(String[] args) {

        SpringApplication.run(WebApp.class, args);
    }
}
