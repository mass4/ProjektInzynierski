package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;

/**
 * TemperatureSensorRepository is used to connect with DB and operate on temperature sensors
 */
public interface TemperatureSensorRepository extends JpaRepository<TemperatureSensor, Long> {

    /**
     * updateSensorById allows you to update a temperature sensor by id
     *
     * @param name    Updated temperature sensor name
     * @param address Updated temperature sensor 1-Wire address
     * @param id      Id of temperature sensor to be updated
     */
    @Query("UPDATE temperature_sensor sensor SET sensor.name = ?1, sensor.address = ?2 WHERE sensor.id =?3")
    void updateSensorById(String name, String address, Long id);
}
