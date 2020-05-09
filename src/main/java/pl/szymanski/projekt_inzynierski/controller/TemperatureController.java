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
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.TemperatureSensorService;

@RestController
@RequestMapping("api/sensor/temperature")
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureSensorService temperatureSensorService;

    @GetMapping
    public List<TemperatureSensor> getAll() {
        return temperatureSensorService.getAllSensors();
    }

    @PostMapping
    public boolean addSensor(@RequestBody TemperatureSensor temperatureSensor) {
        temperatureSensorService.createSensor(temperatureSensor);
        return true;
    }

    @GetMapping(value = "/{id}")
    public TemperatureSensor getById(@PathVariable Long id) {
        Optional<TemperatureSensor> sensor = temperatureSensorService.getSensor(id);
        return sensor.orElse(null);
    }

    @PutMapping(value = "/{id}")
    public void updateSensor(@PathVariable Long id, @RequestBody TemperatureSensor temperatureSensor) {
        temperatureSensor.setId(id);
        temperatureSensorService.updateSensor(temperatureSensor);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteSensor(@PathVariable Long id) {
        Optional<TemperatureSensor> moistureSensor = temperatureSensorService.getSensor(id);
        if (moistureSensor.isPresent()) {
            temperatureSensorService.deleteSensor(moistureSensor.get());
            return true;
        } else {
            return false;
        }
    }
}
