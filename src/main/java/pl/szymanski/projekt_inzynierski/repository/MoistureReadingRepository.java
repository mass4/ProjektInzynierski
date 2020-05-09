package pl.szymanski.projekt_inzynierski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szymanski.projekt_inzynierski.model.MoistureReading;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import java.util.Date;
import java.util.List;

public interface MoistureReadingRepository extends JpaRepository<MoistureReading, Float> {

    @Query("SELECT read FROM moisture_reading read WHERE (read.time BETWEEN :startDate AND :endDate)")
    List<MoistureReading> getReadingsBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("DELETE FROM moisture_reading read WHERE read.sensor=:sensor")
    void deleteReadingsBySensorId(@Param("sensor") MoistureSensor sensor);
}
