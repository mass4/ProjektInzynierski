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

@RequiredArgsConstructor
@Service
public class TemperatureReadingService {

    private final TemperatureReadingRepository temperatureReadingRepository;
    private final TemperatureSensorService temperatureSensorService;

    public List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate) {
        List<SensorReading> sensorReadings;

        List<TemperatureReading> temperatureReadings = temperatureReadingRepository.getReadingsBetween(startDate, endDate);
        List<TemperatureSensor> temperatureSensors = temperatureSensorService.getAllSensors();

        sensorReadings = createSensorReadings(temperatureReadings, temperatureSensors);

        return sensorReadings;
    }

    private List<SensorReading> createSensorReadings(List<TemperatureReading> temperatureReadings, List<TemperatureSensor> temperatureSensors) {
        List<SensorReading> sensorReadings = new ArrayList<>();

        for (TemperatureSensor temperatureSensor : temperatureSensors) {
            sensorReadings.add(new SensorReading(temperatureSensor.getName()));
        }

        return getSensorReadingsFromTemperatureReading(temperatureReadings, sensorReadings);
    }

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
