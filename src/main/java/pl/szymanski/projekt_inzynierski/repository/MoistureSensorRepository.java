package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;

/**
 * MoistureSensorRepository is used to connect with DB and operate on moisture sensors
 * @author Marek Szymański
 */
public interface MoistureSensorRepository extends JpaRepository<MoistureSensor, Long> {

    /**
     * updateSensorById allows you to update a moisture sensor by id
     *
     * @param name    Updated moisture sensor name
     * @param channel Updated moisture sensor channel
     * @param id      Id of moisture sensor to be updated
     */
    @Query("UPDATE moisture_sensor sensor SET sensor.name = ?1, sensor.channel = ?2 WHERE sensor.id =?3")
    void updateSensorById(String name, int channel, Long id);
}
