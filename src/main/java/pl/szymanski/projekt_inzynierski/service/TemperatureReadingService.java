package pl.szymanski.projekt_inzynierski.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        return createSensorReadings(temperatureReadings, temperatureSensors);
    }

    /**
     * Method that helps to process data containing readings from temperature sensors
     *
     * @param temperatureReadings Contains readings from all moisture sensors
     * @param temperatureSensors  List of all moisture sensors
     * @return List of sensor readings for all temperature sensors
     */
    private List<SensorReading> createSensorReadings(List<TemperatureReading> temperatureReadings, List<TemperatureSensor> temperatureSensors) {
        List<SensorReading> sensorReadings = new ArrayList<>();

        for (TemperatureSensor temperatureSensor : temperatureSensors) {
            sensorReadings.add(new SensorReading(temperatureSensor.getName()));
        }

        return getSensorReadingsFromTemperatureReading(temperatureReadings, sensorReadings);
    }

    /**
     * This method segregates readings for individual sensors
     *
     * @param temperatureReadings Contains readings from all temperature sensors
     * @param sensorReadings      List of all temperature sensors
     * @return List of sensor readings for all temperature sensors
     */
    private List<SensorReading> getSensorReadingsFromTemperatureReading(List<TemperatureReading> temperatureReadings, List<SensorReading> sensorReadings) {
        for (TemperatureReading temperatureReading : temperatureReadings) {
            for (SensorReading sensorReading : sensorReadings) {
                if (temperatureReading.getSensor().getName().equals(sensorReading.getName())) {
                    float readValue = temperatureReading.getValue();
                    Date readTime = temperatureReading.getTime();

                    SingleReading read = new SingleReading(readValue, readTime);
                    sensorReading.addToList(read);
                    break;
                }
            }
        }
        return sensorReadings;
    }
}
