package pl.szymanski.projekt_inzynierski.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.repository.MoistureReadingRepository;
import pl.szymanski.projekt_inzynierski.repository.MoistureSensorRepository;

/**
 * MoistureSensorService uses MoistureSensorRepository
 * to operate on the moisture sensor provided by Repository
 */
@RequiredArgsConstructor
@Service
public class MoistureSensorService {
    private final MoistureSensorRepository moistureSensorRepository;
    private final MoistureReadingRepository moistureReadingRepository;

    /**
     * Creates a new moisture sensor and adds it to the database
     * @param name Name of the moisture sensor
     * @param channel Channel of the moisture sensor
     */
    public void createSensor(String name, int channel) {
        MoistureSensor sensorToCreate = new MoistureSensor(name, channel);
        moistureSensorRepository.save(sensorToCreate);
    }

    /**
     * Creates a new moisture sensor and adds it to the database
     * @param moistureSensor {@link MoistureSensor}
     */
    public void createSensor(MoistureSensor moistureSensor) {
        moistureSensorRepository.save(moistureSensor);
    }

    /**
     * Returns all moisture sensors
     * @return List containing all moisture sensors
     */
    public List<MoistureSensor> getAllSensors() {
        return moistureSensorRepository.findAll();
    }

    /**
     * Deletes the moisture sensor from database
     * @param moistureSensor Moisture sensor {@link MoistureSensor} to delete
     */
    public void deleteSensor(MoistureSensor moistureSensor) {
        moistureReadingRepository.deleteReadingsBySensorId(moistureSensor);
        moistureSensorRepository.delete(moistureSensor);
    }

    /**
     * Returns moisture sensor by id
     * @param id Id of the moisture sensor you want to receive
     * @return Return a single MoistureSensor {@link MoistureSensor} object
     */
    public Optional<MoistureSensor> getSensor(Long id) {
        return moistureSensorRepository.findById(id);
    }

    /**
     * Allows to update a specific moisture sensor
     * @param moistureSensor Updated moisture sensor
     */
    public void updateSensor(MoistureSensor moistureSensor) {
        moistureSensorRepository.updateSensorById(moistureSensor.getName(), moistureSensor.getChannel(), moistureSensor.getId());
    }
}
