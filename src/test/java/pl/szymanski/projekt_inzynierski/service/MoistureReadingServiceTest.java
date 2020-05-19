package pl.szymanski.projekt_inzynierski.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.szymanski.projekt_inzynierski.model.MoistureReading;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.model.SensorReading;
import pl.szymanski.projekt_inzynierski.model.SingleReading;
import pl.szymanski.projekt_inzynierski.repository.MoistureReadingRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MoistureReadingServiceTest {

    @Mock
    private MoistureSensorService moistureSensorService;

    @Mock
    private MoistureReadingRepository moistureReadingRepository;

    @InjectMocks
    private MoistureReadingService moistureReadingService;

    private static final String MOISTURE_SENSOR = "Wilgotnosc - Sonda";

    @Test
    public void shouldReturnSensorReadings() {
        //given
        given(moistureReadingRepository.getReadingsBetween(any(Date.class), any(Date.class)))
                .willReturn(readings());
        given(moistureSensorService.getAllSensors()).willReturn(sensors());

        //when
        List<SensorReading> sensorReadings = moistureReadingService.getSensorReadingsBetween(new Date(1L), new Date(1000L));

        //then
        then(moistureReadingRepository).should(times(1)).getReadingsBetween(any(Date.class), any(Date.class));
        then(moistureSensorService).should(times(1)).getAllSensors();
        assertEquals(expectedSensorReadings(), sensorReadings);
    }

    private List<MoistureReading> readings() {
        List<MoistureReading> readings = new ArrayList<>();

        readings.add(new MoistureReading(1L, new Date(100L), 12f, sensors().get(0)));
        readings.add(new MoistureReading(2L, new Date(100L), 21f, sensors().get(1)));
        readings.add(new MoistureReading(3L, new Date(100L), 24f, sensors().get(2)));

        readings.add(new MoistureReading(4L, new Date(200L), 16f, sensors().get(0)));
        readings.add(new MoistureReading(5L, new Date(200L), 19f, sensors().get(1)));
        readings.add(new MoistureReading(6L, new Date(200L), 23f, sensors().get(2)));

        return readings;
    }

    private List<MoistureSensor> sensors() {
        List<MoistureSensor> sensors = new ArrayList<>();
        sensors.add(new MoistureSensor(1L, MOISTURE_SENSOR + " 1", 1));
        sensors.add(new MoistureSensor(2L, MOISTURE_SENSOR + " 2", 2));
        sensors.add(new MoistureSensor(3L, MOISTURE_SENSOR + " 3", 3));
        return sensors;
    }

    private List<SensorReading> expectedSensorReadings() {
        List<SensorReading> sensorReadings = new ArrayList<>();

        sensorReadings.add(new SensorReading(MOISTURE_SENSOR + " 1"));
        sensorReadings.add(new SensorReading(MOISTURE_SENSOR + " 2"));
        sensorReadings.add(new SensorReading(MOISTURE_SENSOR + " 3"));

        sensorReadings.get(0).addToList(new SingleReading(12f, new Date(100L)));
        sensorReadings.get(0).addToList(new SingleReading(16f, new Date(200L)));

        sensorReadings.get(1).addToList(new SingleReading(21f, new Date(100L)));
        sensorReadings.get(1).addToList(new SingleReading(19f, new Date(200L)));

        sensorReadings.get(2).addToList(new SingleReading(24f, new Date(100L)));
        sensorReadings.get(2).addToList(new SingleReading(23f, new Date(200L)));

        return sensorReadings;
    }
}
