package pl.szymanski.projekt_inzynierski.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.projekt_inzynierski.model.SensorReading;
import pl.szymanski.projekt_inzynierski.model.SingleReading;
import pl.szymanski.projekt_inzynierski.model.TemperatureReading;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import pl.szymanski.projekt_inzynierski.repository.TemperatureReadingRepository;

/**
 * TemperatureReadingService uses TemperatureReadingRepository to operate
 * on readings from the temperature sensor provided by Repository
 *
 * @author Marek Szymański
 */
@RequiredArgsConstructor
@Service
public class TemperatureReadingService {

    private final TemperatureReadingRepository temperatureReadingRepository;
    private final TemperatureSensorService temperatureSensorService;

    /**
     * getSensorReadingsBetween method provides you readings of all available
     * sensors from the date range in the form of a SensorReading list
     *
     * @param startDate Date from which the readings are to be found
     * @param endDate   Date by which the searched readings are to be completed
     * @return List of temperature sensor readings for all sensors
     */
    public List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate) {
        List<TemperatureReading> temperatureReadings = temperatureReadingRepository.getReadingsBetween(startDate, endDate);
        List<TemperatureSensor> temperatureSensors = temperatureSensorService.getAllSensors();

        return formatDataToSensorReadings(temperatureReadings, temperatureSensors);
    }

    /**
     * Method that helps to process data containing readings from temperature sensors
     *
     * @param temperatureReadings Contains readings from all moisture sensors
     * @param temperatureSensors  List of all moisture sensors
     * @return List of sensor readings for all temperature sensors
     */
    private List<SensorReading> formatDataToSensorReadings(List<TemperatureReading> temperatureReadings, List<TemperatureSensor> temperatureSensors) {
        List<SensorReading> sensorReadings = temperatureSensors.stream()
                .map(temperatureSensor -> new SensorReading(temperatureSensor.getName()))
                .collect(Collectors.toList());

        sensorReadings.forEach(sensorReading -> assignReadingsToSensor(sensorReading, temperatureReadings));

        return sensorReadings;
    }

    /**
     * This method segregates readings for individual sensors
     *
     * @param temperatureReadings Contains readings from all temperature sensors
     * @param sensorReading       Sensor for which readings should be assigned
     * @return SensorReading with associated readings
     */
    private SensorReading assignReadingsToSensor(SensorReading sensorReading, List<TemperatureReading> temperatureReadings) {
        temperatureReadings.stream()
                .filter(Objects::nonNull)
                .filter(temperatureReading -> temperatureReading.getSensor().getName().equals(sensorReading.getName()))
                .map(temperatureReading -> new SingleReading(temperatureReading.getValue(), temperatureReading.getTime()))
                .forEach(sensorReading::addToList);

        return sensorReading;
    }
}