package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szymanski.ProjektInzynierski.model.TemperatureReading;
import pl.szymanski.ProjektInzynierski.repository.TemperatureReadingRepo;

import java.util.List;

@Controller
public class TemperatureReadingController {

    @Autowired
    TemperatureReadingRepo temperatureReadingRepo;

    @RequestMapping("/get/temperature")
    public String getRead(Model model){
        List<TemperatureReading> temperatureReadings = temperatureReadingRepo.getAllReadings();
        model.addAttribute("temperatureReadings",temperatureReadings);
        return "temperatureSensorsReading";
    }
}
