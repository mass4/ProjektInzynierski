package pl.szymanski.ProjektInzynierski.repository;

import pl.szymanski.ProjektInzynierski.model.TemperatureReading;

import java.util.Date;
import java.util.List;

public interface TemperatureReadingRepository {
    List<TemperatureReading> getAllReadings();
    List<TemperatureReading> getReadingsBetween(Date startDate, Date endDate);
}
