package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szymanski.projekt_inzynierski.model.MoistureReading;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import java.util.Date;
import java.util.List;

/**
 * MoistureReadingRepository is used to connect with DB and operate on readings from moisture sensor
 */
public interface MoistureReadingRepository extends JpaRepository<MoistureReading, Float> {

    /**
     * getReadingsBetween returns a readings between two dates which you provide as two params
     *
     * @param startDate Date from which the readings are to be found
     * @param endDate   Date by which the searched readings are to be completed
     * @return List of results from DB
     */
    @Query("SELECT read FROM moisture_reading read WHERE (read.time BETWEEN :startDate AND :endDate)")
    List<MoistureReading> getReadingsBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * deleteReadingsBySensorId deletes all readings that have been collected for the moisture sensor
     *
     * @param sensor Moisture sensor for which the readings will be deleted
     */
    @Query("DELETE FROM moisture_reading read WHERE read.sensor=:sensor")
    void deleteReadingsBySensorId(@Param("sensor") MoistureSensor sensor);
}
