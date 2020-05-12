package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import pl.szymanski.projekt_inzynierski.model.TemperatureReading;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;

/**
 * TemperatureReadingRepository is used to connect with DB and operate on readings from temperature sensor
 */
public interface TemperatureReadingRepository extends JpaRepository<TemperatureReading, Long> {

    /**
     * getReadingsBetween returns a readings between two dates which you provide as two params
     *
     * @param startDate Date from which the readings are to be found
     * @param endDate   Date by which the searched readings are to be completed
     * @return List of results from DB
     */
    @Query("SELECT read FROM temperature_reading read WHERE (read.time BETWEEN :startDate AND :endDate)")
    List<TemperatureReading> getReadingsBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * deleteReadingsBySensorId deletes all readings that have been collected for the temperature sensor
     *
     * @param sensor Temperature sensor for which the readings will be deleted
     */
    @Query("DELETE FROM temperature_reading read WHERE read.sensor=:sensor")
    void deleteReadingsBySensorId(@Param("sensor") TemperatureSensor sensor);
}
