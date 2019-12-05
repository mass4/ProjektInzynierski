package pl.szymanski.ProjektInzynierski.repository;

import pl.szymanski.ProjektInzynierski.model.SensorReading;
import pl.szymanski.ProjektInzynierski.model.TemperatureReading;

import java.util.Date;
import java.util.List;

public interface TemperatureReadingRepository {
    List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate);
}
