package pl.szymanski.projekt_inzynierski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymanski.projekt_inzynierski.model.SensorReading;

import java.util.Date;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.MoistureReadingService;
import pl.szymanski.projekt_inzynierski.service.TemperatureReadingService;

@RestController
@RequestMapping("api/readings")
@RequiredArgsConstructor
public class ReadingController {

    private final TemperatureReadingService temperatureReadingService;
    private final MoistureReadingService moistureReadingService;

    @GetMapping(value = "/temperature/{startDate}/{endDate}")
    public List<SensorReading> getTemperatureBetween(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) {
        return temperatureReadingService.getSensorReadingsBetween(startDate, endDate);
    }

    @GetMapping(value = "/moisture/{startDate}/{endDate}")
    public List<SensorReading> getMoistureBetween(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) {
        return moistureReadingService.getSensorReadingsBetween(startDate, endDate);
    }
}
