package pl.szymanski.projekt_inzynierski.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.MoistureSensorService;

@RestController
@RequestMapping("api/sensor/moisture")
@RequiredArgsConstructor
public class MoistureController {

    private final MoistureSensorService moistureSensorService;

    @GetMapping
    public List<MoistureSensor> getAll() {
        return moistureSensorService.getAllSensors();
    }

    @PostMapping
    public boolean addSensor(@RequestBody MoistureSensor moistureSensor) {
        moistureSensorService.createSensor(moistureSensor);
        return true;
    }

    @GetMapping(value = "/{id}")
    public MoistureSensor getById(@PathVariable Long id) {
        Optional<MoistureSensor> sensor = moistureSensorService.getSensor(id);
        return sensor.orElse(null);
    }

    @PutMapping(value = "/{id}")
    public void updateSensor(@PathVariable Long id, @RequestBody MoistureSensor moistureSensor) {
        moistureSensor.setId(id);
        moistureSensorService.updateSensor(moistureSensor);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteSensor(@PathVariable Long id) {
        Optional<MoistureSensor> moistureSensor = moistureSensorService.getSensor(id);
        if (moistureSensor.isPresent()) {
            moistureSensorService.deleteSensor(moistureSensor.get());
            return true;
        } else {
            return false;
        }
    }
}
