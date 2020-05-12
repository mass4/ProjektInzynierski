package pl.szymanski.projekt_inzynierski.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        List<SensorReading> sensorReadings = new ArrayList<>();

        for (MoistureSensor moistureSensor : moistureSensors) {
            sensorReadings.add(new SensorReading(moistureSensor.getName()));
        }

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
        for (MoistureReading moistureReading : moistureReadings) {
            for (SensorReading sensorReading : sensorReadings) {
                if (moistureReading.getSensor().getName().equals(sensorReading.getName())) {
                    float readValue = moistureReading.getValue();
                    Date readTime = moistureReading.getTime();

                    SingleReading read = new SingleReading(readValue, readTime);
                    sensorReading.addToList(read);
                    break;
                }
            }
        }
        return sensorReadings;
    }
}
