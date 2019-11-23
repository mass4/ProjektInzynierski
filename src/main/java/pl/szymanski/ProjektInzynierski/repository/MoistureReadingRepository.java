package pl.szymanski.ProjektInzynierski.repository;

import pl.szymanski.ProjektInzynierski.model.MoistureReading;

import java.util.Date;
import java.util.List;

public interface MoistureReadingRepository {
    List<MoistureReading> getAllReadings();
    List<MoistureReading> getReadingsBetween(Date startDate, Date endDate);
}
