package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.MoistureSensor;

import java.util.List;


public interface MoistureSensorRepository {
    void createSensor(String name, int channel);

    void createSensor(MoistureSensor moistureSensor);

    List<MoistureSensor> getAllSensors();

    void deleteSensor(MoistureSensor moistureSensor);

    MoistureSensor getSensor(Long id);

    void updateSensor (MoistureSensor moistureSensor);
}
