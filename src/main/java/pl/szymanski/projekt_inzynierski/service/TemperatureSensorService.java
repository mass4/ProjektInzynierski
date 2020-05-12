package pl.szymanski.projekt_inzynierski.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import pl.szymanski.projekt_inzynierski.repository.TemperatureReadingRepository;
import pl.szymanski.projekt_inzynierski.repository.TemperatureSensorRepository;

/**
 * TemperatureSensorService uses TemperatureSensorRepository
 * to operate on the temperature sensor provided by Repository
 */
@RequiredArgsConstructor
@Service
public class TemperatureSensorService {
    private final TemperatureSensorRepository temperatureSensorRepository;
    private final TemperatureReadingRepository temperatureReadingRepository;

    /**
     * Creates a new temperature sensor and adds it to the database
     *
     * @param name    Name of the temperature sensor
     * @param address 1-Wire Address of the temperature sensor
     */
    public void createSensor(String name, String address) {
        TemperatureSensor sensorToCreate = new TemperatureSensor(name, address);
        temperatureSensorRepository.save(sensorToCreate);
    }

    /**
     * Creates a new temperature sensor and adds it to the database
     *
     * @param temperatureSensor {@link TemperatureSensor}
     */
    public void createSensor(TemperatureSensor temperatureSensor) {
        temperatureSensorRepository.save(temperatureSensor);
    }

    /**
     * Returns all temperature sensors
     *
     * @return List containing all temperature sensors
     */
    public List<TemperatureSensor> getAllSensors() {
        return temperatureSensorRepository.findAll();
    }

    /**
     * Deletes the moisture sensor from database
     *
     * @param temperatureSensor Temperature sensor {@link TemperatureSensor} to delete
     */
    public void deleteSensor(TemperatureSensor temperatureSensor) {
        temperatureReadingRepository.deleteReadingsBySensorId(temperatureSensor);
        temperatureSensorRepository.delete(temperatureSensor);
    }

    /**
     * Returns temperature sensor by id
     *
     * @param id Id of the temperature sensor you want to receive
     * @return Return a single TemperatureSensor {@link TemperatureSensor} object
     */
    public Optional<TemperatureSensor> getSensor(Long id) {
        return temperatureSensorRepository.findById(id);
    }

    /**
     * Allows to update a specific temperature sensor
     *
     * @param temperatureSensor Updated temperature sensor
     */
    public void updateSensor(TemperatureSensor temperatureSensor) {
        temperatureSensorRepository.updateSensorById(temperatureSensor.getName(), temperatureSensor.getAddress(), temperatureSensor.getId());
    }
}
