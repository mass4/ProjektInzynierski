package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymanski.ProjektInzynierski.model.SensorReading;
import pl.szymanski.ProjektInzynierski.repository.MoistureReadingRepo;
import pl.szymanski.ProjektInzynierski.repository.TemperatureReadingRepo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/readings")
public class ReadingController {

    public static Long HOUR = 3600L*1000L;

    @Autowired
    private TemperatureReadingRepo temperatureReadingRepo;

    @Autowired
    private MoistureReadingRepo moistureReadingRepo;

    @GetMapping(value = "/temperature")
    public List<SensorReading> getAllTemperature(){
        return temperatureReadingRepo.getAllSensorReadings();
    }

    @GetMapping(value = "/temperature/hour")
    public List<SensorReading> getHourTemperature(){
        Date actual = new Date(System.currentTimeMillis());
        Date beforeActual24h = new Date(actual.getTime()-HOUR);
        return temperatureReadingRepo.getSensorReadingsBetween(beforeActual24h,actual);
    }

    @GetMapping(value = "/temperature/24h")
    public List<SensorReading> get24hTemperature(){
        Date actual = new Date(System.currentTimeMillis());
        Date beforeActual24h = new Date(actual.getTime()-HOUR*24);
        return temperatureReadingRepo.getSensorReadingsBetween(beforeActual24h,actual);
    }

    @GetMapping(value = "/temperature/month")
    public List<SensorReading> getMonthTemperature(){
        Date actual = new Date(System.currentTimeMillis());
        Date beforeActual24h = new Date(actual.getTime()-HOUR*24*30);
        return temperatureReadingRepo.getSensorReadingsBetween(beforeActual24h,actual);
    }

    @GetMapping(value = "/temperature/{startDate}/{endDate}")
    public List<SensorReading> getTemperatureBetween(@PathVariable("startDate") Long startDate, @PathVariable("endDate") Long endDate) {
        Date start = new Date(startDate);
        Date end = new Date(endDate);
        return temperatureReadingRepo.getSensorReadingsBetween(start,end);
    }

    @GetMapping(value = "/moisture")
    public List<SensorReading> getAllMoisture(){
        return moistureReadingRepo.getAllSensorReadings();
    }

    @GetMapping(value = "/moisture/hour")
    public List<SensorReading> getHourMoisture(){
        Date actual = new Date(System.currentTimeMillis());
        Date beforeActual24h = new Date(actual.getTime()-HOUR);
        return moistureReadingRepo.getSensorReadingsBetween(beforeActual24h,actual);
    }

    @GetMapping(value = "/moisture/24h")
    public List<SensorReading> get24hMoisture(){
        Date actual = new Date(System.currentTimeMillis());
        Date beforeActual24h = new Date(actual.getTime()-HOUR*24);
        return moistureReadingRepo.getSensorReadingsBetween(beforeActual24h,actual);
    }

    @GetMapping(value = "/moisture/month")
    public List<SensorReading> getMonthMoisture(){
        Date actual = new Date(System.currentTimeMillis());
        Date beforeActual24h = new Date(actual.getTime()-HOUR*24*30);
        return moistureReadingRepo.getSensorReadingsBetween(beforeActual24h,actual);
    }

    @GetMapping(value = "/moisture/{startDate}/{endDate}")
    public List<SensorReading> getMoistureBetween(@PathVariable("startDate") Long startDate, @PathVariable("endDate") Long endDate) {
        Date start = new Date(startDate);
        Date end = new Date(endDate);
        return moistureReadingRepo.getSensorReadingsBetween(start,end);
    }
}
