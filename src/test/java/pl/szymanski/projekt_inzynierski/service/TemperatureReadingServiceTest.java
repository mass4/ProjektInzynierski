package pl.szymanski.projekt_inzynierski.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.szymanski.projekt_inzynierski.model.SensorReading;
import pl.szymanski.projekt_inzynierski.model.SingleReading;
import pl.szymanski.projekt_inzynierski.model.TemperatureReading;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import pl.szymanski.projekt_inzynierski.repository.TemperatureReadingRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TemperatureReadingServiceTest {

    @Mock
    private TemperatureSensorService temperatureSensorService;

    @Mock
    private TemperatureReadingRepository temperatureReadingRepository;

    @InjectMocks
    private TemperatureReadingService temperatureReadingService;

    private static final String TEMPERATURE_SENSOR = "Temperatura - Sonda";

    @Test
    public void shouldReturnSensorReadings() {
        //given
        given(temperatureReadingRepository.getReadingsBetween(any(Date.class), any(Date.class)))
                .willReturn(readings());
        given(temperatureSensorService.getAllSensors()).willReturn(sensors());

        //when
        List<SensorReading> sensorReadings = temperatureReadingService.getSensorReadingsBetween(new Date(1L), new Date(1000L));

        //then
        then(temperatureReadingRepository).should(times(1)).getReadingsBetween(any(Date.class), any(Date.class));
        then(temperatureSensorService).should(times(1)).getAllSensors();
        assertEquals(expectedSensorReadings(), sensorReadings);
    }

    private List<TemperatureReading> readings() {
        List<TemperatureReading> readings = new ArrayList<>();

        readings.add(new TemperatureReading(1L, new Date(100L), 12f, sensors().get(0)));
        readings.add(new TemperatureReading(2L, new Date(100L), 21f, sensors().get(1)));
        readings.add(new TemperatureReading(3L, new Date(100L), 24f, sensors().get(2)));

        readings.add(new TemperatureReading(4L, new Date(200L), 16f, sensors().get(0)));
        readings.add(new TemperatureReading(5L, new Date(200L), 19f, sensors().get(1)));
        readings.add(new TemperatureReading(6L, new Date(200L), 23f, sensors().get(2)));

        return readings;
    }

    private List<TemperatureSensor> sensors() {
        List<TemperatureSensor> sensors = new ArrayList<>();
        sensors.add(new TemperatureSensor(1L, TEMPERATURE_SENSOR + " 1", "10-0000000"));
        sensors.add(new TemperatureSensor(2L, TEMPERATURE_SENSOR + " 2", "20-0000000"));
        sensors.add(new TemperatureSensor(3L, TEMPERATURE_SENSOR + " 3", "30-0000000"));
        return sensors;
    }

    private List<SensorReading> expectedSensorReadings() {
        List<SensorReading> sensorReadings = new ArrayList<>();

        sensorReadings.add(new SensorReading(TEMPERATURE_SENSOR + " 1"));
        sensorReadings.add(new SensorReading(TEMPERATURE_SENSOR + " 2"));
        sensorReadings.add(new SensorReading(TEMPERATURE_SENSOR + " 3"));

        sensorReadings.get(0).addToList(new SingleReading(12f, new Date(100L)));
        sensorReadings.get(0).addToList(new SingleReading(16f, new Date(200L)));

        sensorReadings.get(1).addToList(new SingleReading(21f, new Date(100L)));
        sensorReadings.get(1).addToList(new SingleReading(19f, new Date(200L)));

        sensorReadings.get(2).addToList(new SingleReading(24f, new Date(100L)));
        sensorReadings.get(2).addToList(new SingleReading(23f, new Date(200L)));

        return sensorReadings;
    }
}
