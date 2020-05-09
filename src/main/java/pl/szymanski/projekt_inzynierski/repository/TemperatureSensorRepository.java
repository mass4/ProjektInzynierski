package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;

public interface TemperatureSensorRepository extends JpaRepository<TemperatureSensor, Long> {

    @Query("UPDATE temperature_sensor sensor SET sensor.name = ?1, sensor.address = ?2 WHERE sensor.id =?3")
    void updateSensorById(String name, String address, Long id);
}
