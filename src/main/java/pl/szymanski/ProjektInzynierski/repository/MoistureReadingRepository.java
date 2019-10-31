package pl.szymanski.ProjektInzynierski.repository;

import pl.szymanski.ProjektInzynierski.model.MoistureReading;

import java.util.List;

public interface MoistureReadingRepository {
    List<MoistureReading> getAllReadings();
}
