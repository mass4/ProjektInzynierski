package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import pl.szymanski.projekt_inzynierski.model.TemperatureReading;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;

public interface TemperatureReadingRepository extends JpaRepository<TemperatureReading, Long> {

    @Query("SELECT read FROM temperature_reading read WHERE (read.time BETWEEN :startDate AND :endDate)")
    List<TemperatureReading> getReadingsBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("DELETE FROM temperature_reading read WHERE read.sensor=:sensor")
    void deleteReadingsBySensorId(@Param("sensor") TemperatureSensor sensor);
}
