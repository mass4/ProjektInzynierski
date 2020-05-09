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

@RequiredArgsConstructor
@Service
public class MoistureReadingService {

    private final MoistureReadingRepository moistureReadingRepository;
    private final MoistureSensorService moistureSensorService;

    public List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate) {
        List<SensorReading> sensorReadings;

        List<MoistureReading> moistureReadings = moistureReadingRepository.getReadingsBetween(startDate, endDate);
        List<MoistureSensor> moistureSensors = moistureSensorService.getAllSensors();

        return createSensorReadings(moistureReadings, moistureSensors);
    }


    private List<SensorReading> createSensorReadings(List<MoistureReading> moistureReadings, List<MoistureSensor> moistureSensors) {
        List<SensorReading> sensorReadings = new ArrayList<>();

        for (MoistureSensor moistureSensor : moistureSensors) {
            sensorReadings.add(new SensorReading(moistureSensor.getName()));
        }

        return getSensorReadingsFromMoistureReading(moistureReadings, sensorReadings);
    }

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
