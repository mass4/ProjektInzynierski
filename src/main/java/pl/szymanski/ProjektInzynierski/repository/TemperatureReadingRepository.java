package pl.szymanski.ProjektInzynierski.repository;

import pl.szymanski.ProjektInzynierski.model.TemperatureReading;

import java.util.List;

public interface TemperatureReadingRepository {
    List<TemperatureReading> getAllReadings();
}
