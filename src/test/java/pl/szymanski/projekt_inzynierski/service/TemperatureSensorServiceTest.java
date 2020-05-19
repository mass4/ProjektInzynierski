package pl.szymanski.projekt_inzynierski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import pl.szymanski.projekt_inzynierski.repository.TemperatureSensorRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureSensorServiceTest {

    @Mock
    private TemperatureSensorRepository temperatureSensorRepository;

    @InjectMocks
    private TemperatureSensorService temperatureSensorService;

    private static final String TEMPERATURE_SENSOR = "Temperatura - Sonda";

    @Test
    public void shouldReturnAllSensors() {
        //given
        given(temperatureSensorRepository.findAll()).willReturn(sensors());

        //when
        List<TemperatureSensor> sensorsFromService = temperatureSensorService.getAllSensors();

        //then
        then(temperatureSensorRepository).should(times(1)).findAll();
        assertEquals(3, sensorsFromService.size());
    }

    @Test
    public void shouldReturnSensorById() {
        //given
        given(temperatureSensorRepository.findById(2L)).willReturn(Optional.of(sensors().get(1)));

        //when
        Optional<TemperatureSensor> sensorFromService = temperatureSensorService.getSensor(2L);

        //then
        then(temperatureSensorRepository).should(times(1)).findById(any(Long.class));
        assertTrue(sensorFromService.isPresent());
        assertEquals(Long.valueOf(2L), sensorFromService.get().getId());
    }

    @Test
    public void shouldTryCreateSensor() {
        //given
        TemperatureSensor sensorToCreate = sensors().get(0);

        //when
        temperatureSensorService.createSensor(sensorToCreate);

        //then
        then(temperatureSensorRepository).should(times(1)).save(any(TemperatureSensor.class));
    }

    @Test
    public void shouldTryCreateSensorByValues() {
        //given
        TemperatureSensor sensorToCreate = sensors().get(0);

        //when
        temperatureSensorService.createSensor(sensorToCreate.getName(), sensorToCreate.getAddress());

        //then
        then(temperatureSensorRepository).should(times(1)).save(any(TemperatureSensor.class));
    }

    @Test
    public void shouldTryUpdateSensor() {
        //given
        TemperatureSensor sensorToUpdate = sensors().get(0);

        //when
        temperatureSensorService.updateSensor(sensorToUpdate);

        //then
        then(temperatureSensorRepository).should(times(1)).updateSensorById(any(String.class), any(String.class), any(Long.class));
    }

    private List<TemperatureSensor> sensors() {
        List<TemperatureSensor> sensors = new ArrayList<>();
        sensors.add(new TemperatureSensor(1L, TEMPERATURE_SENSOR + " 1", "10-0000000"));
        sensors.add(new TemperatureSensor(2L, TEMPERATURE_SENSOR + " 2", "20-0000000"));
        sensors.add(new TemperatureSensor(3L, TEMPERATURE_SENSOR + " 3", "30-0000000"));
        return sensors;
    }
}
