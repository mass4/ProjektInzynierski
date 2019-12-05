package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;
import pl.szymanski.ProjektInzynierski.repository.TemperatureSensorRepo;

import java.util.List;

@RestController
@RequestMapping("api/sensor/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureSensorRepo temperatureSensorRepo;

    @GetMapping
    public List<TemperatureSensor> getAll(){
        return temperatureSensorRepo.getAllSensors();
    }

    @PostMapping
    public boolean addSensor(@RequestBody TemperatureSensor temperatureSensor) {
        temperatureSensorRepo.createSensor(temperatureSensor);
        return true;
    }

    @GetMapping(value = "/{id}")
    public TemperatureSensor getById(@PathVariable Long id){
        TemperatureSensor temperatureSensor = temperatureSensorRepo.getSensor(id);
        return temperatureSensor;
    }

    @PutMapping(value="/{id}")
    public boolean updateSensor(@PathVariable Long id, @RequestBody TemperatureSensor temperatureSensor){
        temperatureSensor.setId(id);
        temperatureSensorRepo.updateSensor(temperatureSensor);
        return true;
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteSensor(@PathVariable Long id){
        TemperatureSensor temperatureSensor = temperatureSensorRepo.getSensor(id);
        if (temperatureSensor == null){
            return false;
        }
        temperatureSensorRepo.deleteSensor(temperatureSensor);
        return true;
    }
}
