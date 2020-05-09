package pl.szymanski.projekt_inzynierski.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import pl.szymanski.projekt_inzynierski.repository.TemperatureReadingRepository;
import pl.szymanski.projekt_inzynierski.repository.TemperatureSensorRepository;

@RequiredArgsConstructor
@Service
public class TemperatureSensorService {
    private final TemperatureSensorRepository temperatureSensorRepository;
    private final TemperatureReadingRepository temperatureReadingRepository;

    public void createSensor(String name, String address) {
        TemperatureSensor sensorToCreate = new TemperatureSensor(name, address);
        temperatureSensorRepository.save(sensorToCreate);
    }

    public void createSensor(TemperatureSensor temperatureSensor) {
        temperatureSensorRepository.save(temperatureSensor);
    }

    public List<TemperatureSensor> getAllSensors() {
        return temperatureSensorRepository.findAll();
    }

    public void deleteSensor(TemperatureSensor temperatureSensor) {
        temperatureReadingRepository.deleteReadingsBySensorId(temperatureSensor);
        temperatureSensorRepository.delete(temperatureSensor);
    }

    public Optional<TemperatureSensor> getSensor(Long id) {
        return temperatureSensorRepository.findById(id);
    }

    public void updateSensor(TemperatureSensor temperatureSensor) {
        temperatureSensorRepository.updateSensorById(temperatureSensor.getName(), temperatureSensor.getAddress(), temperatureSensor.getId());
    }
}
