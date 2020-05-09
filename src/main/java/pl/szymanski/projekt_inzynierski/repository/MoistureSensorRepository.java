package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;

public interface MoistureSensorRepository extends JpaRepository<MoistureSensor, Long> {

    @Query("UPDATE moisture_sensor sensor SET sensor.name = ?1, sensor.channel = ?2 WHERE sensor.id =?3")
    void updateSensorById(String name, int channel, Long id);
}
