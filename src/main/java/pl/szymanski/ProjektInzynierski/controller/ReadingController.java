package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.szymanski.ProjektInzynierski.model.SensorReading;
import pl.szymanski.ProjektInzynierski.repository.MoistureReadingRepo;
import pl.szymanski.ProjektInzynierski.repository.TemperatureReadingRepo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/readings")
public class ReadingController {

    public static Long HOUR = 3600L*1000L;

    @Autowired
    private TemperatureReadingRepo temperatureReadingRepo;

    @Autowired
    private MoistureReadingRepo moistureReadingRepo;

//    @GetMapping(value = "/temperature/{startDate}/{endDate}")
//    public List<SensorReading> getTemperatureBetween(@PathVariable("startDate") Long startDate, @PathVariable("endDate") Long endDate) {
//        Date start = new Date(startDate);
//        Date end = new Date(endDate);
//        return temperatureReadingRepo.getSensorReadingsBetween(start,end);
//    }
//
//    @GetMapping(value = "/moisture/{startDate}/{endDate}")
//    public List<SensorReading> getMoistureBetween(@PathVariable("startDate") Long startDate, @PathVariable("endDate") Long endDate) {
//        Date start = new Date(startDate);
//        Date end = new Date(endDate);
//        return moistureReadingRepo.getSensorReadingsBetween(start,end);
//    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/temperature/{startDate}/{endDate}")
    public List<SensorReading> getTemperatureBetween(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) {
        return temperatureReadingRepo.getSensorReadingsBetween(startDate,endDate);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/moisture/{startDate}/{endDate}")
    public List<SensorReading> getMoistureBetween(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) {
        return moistureReadingRepo.getSensorReadingsBetween(startDate,endDate);
    }
}
