package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.szymanski.ProjektInzynierski.model.MoistureSensor;
import pl.szymanski.ProjektInzynierski.repository.MoistureSensorRepo;

import java.util.List;

@RestController
@RequestMapping("api/sensor/moisture")
public class MoistureController {

    @Autowired
    private MoistureSensorRepo moistureSensorRepo;

    @GetMapping
    public List<MoistureSensor> getAll(){
        return moistureSensorRepo.getAllSensors();
    }

    @PostMapping
    public boolean addSensor(@RequestBody MoistureSensor moistureSensor) {
        moistureSensorRepo.createSensor(moistureSensor);
        return true;
    }

    @GetMapping(value = "/{id}")
    public MoistureSensor getById(@PathVariable Long id){
        MoistureSensor moistureSensor = moistureSensorRepo.getSensor(id);
        return moistureSensor;
    }

    @PutMapping(value="/{id}")
    public boolean updateSensor(@PathVariable Long id, @RequestBody MoistureSensor moistureSensor){
        moistureSensor.setId(id);
        moistureSensorRepo.updateSensor(moistureSensor);
        return true;
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteSensor(@PathVariable Long id){
        MoistureSensor moistureSensor = moistureSensorRepo.getSensor(id);
        if (moistureSensor == null){
            return false;
        }
        moistureSensorRepo.deleteSensor(moistureSensor);
        return true;
    }
}
