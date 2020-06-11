package pl.szymanski.projekt_inzynierski.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.projekt_inzynierski.model.MoistureReading;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.model.SensorReading;
import pl.szymanski.projekt_inzynierski.model.SingleReading;
import pl.szymanski.projekt_inzynierski.repository.MoistureReadingRepository;

/**
 * MoistureReadingService uses MoistureReadingRepository to operate
 * on readings from the moisture sensor provided by Repository
 *
 * @author Marek Szymański
 */
@RequiredArgsConstructor
@Service
public class MoistureReadingService {

    private final MoistureReadingRepository moistureReadingRepository;
    private final MoistureSensorService moistureSensorService;

    /**
     * getSensorReadingsBetween method provides you readings of all available
     * sensors from the date range in the form of a SensorReading list
     *
     * @param startDate Date from which the readings are to be found
     * @param endDate   Date by which the searched readings are to be completed
     * @return List of moisture sensor readings for all sensors
     */
    public List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate) {
        List<MoistureReading> moistureReadings = moistureReadingRepository.getReadingsBetween(startDate, endDate);
        List<MoistureSensor> moistureSensors = moistureSensorService.getAllSensors();

        return createSensorReadings(moistureReadings, moistureSensors);
    }

    /**
     * Method that helps to process data containing readings from moisture sensors
     *
     * @param moistureReadings Contains readings from all moisture sensors
     * @param moistureSensors  List of all moisture sensors
     * @return List of sensor readings for all moisture sensors
     */
    private List<SensorReading> createSensorReadings(List<MoistureReading> moistureReadings, List<MoistureSensor> moistureSensors) {
        List<SensorReading> sensorReadings = moistureSensors.stream()
                .map(moistureSensor -> new SensorReading(moistureSensor.getName()))
                .collect(Collectors.toList());

        return getSensorReadingsFromMoistureReading(moistureReadings, sensorReadings);
    }

    /**
     * This method segregates readings for individual sensors
     *
     * @param moistureReadings Contains readings from all moisture sensors
     * @param sensorReadings   List of all moisture sensors
     * @return List of sensor readings for all moisture sensors
     */
    private List<SensorReading> getSensorReadingsFromMoistureReading(List<MoistureReading> moistureReadings, List<SensorReading> sensorReadings) {
        sensorReadings.forEach(sensorReading -> collectReadings(sensorReading, moistureReadings));

        return sensorReadings;
    }

    private SensorReading collectReadings(SensorReading sensor, List<MoistureReading> moistureReadings) {
        moistureReadings.stream()
                .filter(Objects::nonNull)
                .filter(moistureReading -> moistureReading.getSensor().getName().equals(sensor.getName()))
                .map(moistureReading -> new SingleReading(moistureReading.getValue(), moistureReading.getTime()))
                .forEach(sensor::addToList);

        return sensor;
    }
}