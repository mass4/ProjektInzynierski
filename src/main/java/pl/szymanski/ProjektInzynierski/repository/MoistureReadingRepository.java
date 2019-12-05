package pl.szymanski.ProjektInzynierski.repository;

import pl.szymanski.ProjektInzynierski.model.MoistureReading;
import pl.szymanski.ProjektInzynierski.model.SensorReading;

import java.util.Date;
import java.util.List;

public interface MoistureReadingRepository {
    List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate);
}
