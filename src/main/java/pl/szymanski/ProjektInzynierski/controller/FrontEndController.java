//package pl.szymanski.ProjektInzynierski.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.thymeleaf.util.DateUtils;
//import pl.szymanski.ProjektInzynierski.model.SensorReading;
//import pl.szymanski.ProjektInzynierski.model.TemperatureReading;
//import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;
//import pl.szymanski.ProjektInzynierski.repository.TemperatureReadingRepo;
//import pl.szymanski.ProjektInzynierski.repository.TemperatureSensorRepo;
//
//import java.util.Date;
//import java.util.List;
//
//@RestController
//@RequestMapping("/front")
//public class FrontEndController {
//
//    @Autowired
//    TemperatureReadingRepo temperatureReadingRepo;
//
//    @Autowired
//    TemperatureSensorRepo temperatureSensorRepo;
//
//    @GetMapping(value = "/read/temperature")
//    public List<SensorReading> getAll(){
//        Long HOUR = 3600L*1000L;
//        Date actual = new Date(System.currentTimeMillis());
//        Date beforeActual24h = new Date(actual.getTime()-HOUR*24*30);
//        return temperatureReadingRepo.getSensorReadingsBetween(beforeActual24h,actual);
//    }
//
//    @GetMapping(value = "/read/temperature/{startDate}/{endDate}")
//    public List<SensorReading> getBetween(@PathVariable("startDate") Long startDate,@PathVariable("endDate") Long endDate) {
//        Date start = new Date(startDate);
//        Date end = new Date(endDate);
//        return temperatureReadingRepo.getSensorReadingsBetween(start,end);
//    }
//
//    @PostMapping(value = "/sensor/temperature")
//    public boolean addSensor(@RequestBody TemperatureSensor temperatureSensor) {
//        temperatureSensorRepo.createSensor(temperatureSensor);
//        return true;
//    }
//
//    @PutMapping(value="/sensor/temperature/{id}")
//    public boolean updateSensor(@PathVariable Long id, @RequestBody TemperatureSensor temperatureSensor){
//        temperatureSensor.setId(id);
//        temperatureSensorRepo.updateSensor(temperatureSensor);
//        return true;
//    }
//
//    @DeleteMapping(value = "/sensor/temperature/{id}")
//    public boolean deleteSensor(@PathVariable Long id){
//        TemperatureSensor temperatureSensor = temperatureSensorRepo.getSensor(id);
//        if (temperatureSensor == null){
//            return false;
//        }
//        temperatureSensorRepo.deleteSensor(temperatureSensor);
//        return true;
//    }
//}
