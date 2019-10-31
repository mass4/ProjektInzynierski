package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;

import java.util.List;


public interface TemperatureSensorRepository {
    void createSensor(String name, String address);

    void createSensor(TemperatureSensor temperatureSensor);

    List<TemperatureSensor> getAllSensors();

    void deleteSensor(TemperatureSensor temperatureSensor);

    TemperatureSensor getSensor(Long id);

    void updateSensor (TemperatureSensor temperatureSensor);
}
