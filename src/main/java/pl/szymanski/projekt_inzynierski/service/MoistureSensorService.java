package pl.szymanski.projekt_inzynierski.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.repository.MoistureReadingRepository;
import pl.szymanski.projekt_inzynierski.repository.MoistureSensorRepository;

@RequiredArgsConstructor
@Service
public class MoistureSensorService {
    private final MoistureSensorRepository moistureSensorRepository;
    private final MoistureReadingRepository moistureReadingRepository;

    public void createSensor(String name, int channel) {
        MoistureSensor sensorToCreate = new MoistureSensor(name, channel);
        moistureSensorRepository.save(sensorToCreate);
    }

    public void createSensor(MoistureSensor moistureSensor) {
        moistureSensorRepository.save(moistureSensor);
    }

    public List<MoistureSensor> getAllSensors() {
        return moistureSensorRepository.findAll();
    }

    public void deleteSensor(MoistureSensor moistureSensor) {
        moistureReadingRepository.deleteReadingsBySensorId(moistureSensor);
        moistureSensorRepository.delete(moistureSensor);
    }

    public Optional<MoistureSensor> getSensor(Long id) {
        return moistureSensorRepository.findById(id);
    }

    public void updateSensor(MoistureSensor moistureSensor) {
        moistureSensorRepository.updateSensorById(moistureSensor.getName(), moistureSensor.getChannel(), moistureSensor.getId());
    }
}
