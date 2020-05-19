package pl.szymanski.projekt_inzynierski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.repository.MoistureSensorRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

@RunWith(MockitoJUnitRunner.class)
public class MoistureSensorServiceTest {

    @Mock
    private MoistureSensorRepository moistureSensorRepository;

    @InjectMocks
    private MoistureSensorService moistureSensorService;

    private static final String MOISTURE_SENSOR = "Wilgotnosc - Sonda";

    @Test
    public void shouldReturnAllSensors() {
        //given
        given(moistureSensorRepository.findAll()).willReturn(sensors());

        //when
        List<MoistureSensor> sensorsFromService = moistureSensorService.getAllSensors();

        //then
        then(moistureSensorRepository).should(times(1)).findAll();
        assertEquals(3, sensorsFromService.size());
    }

    @Test
    public void shouldReturnSensorById() {
        //given
        given(moistureSensorRepository.findById(2L)).willReturn(Optional.of(sensors().get(1)));

        //when
        Optional<MoistureSensor> sensorFromService = moistureSensorService.getSensor(2L);

        //then
        then(moistureSensorRepository).should(times(1)).findById(any(Long.class));
        assertTrue(sensorFromService.isPresent());
        assertEquals(Long.valueOf(2L), sensorFromService.get().getId());
    }

    @Test
    public void shouldTryCreateSensor() {
        //given
        MoistureSensor sensorToCreate = sensors().get(0);

        //when
        moistureSensorService.createSensor(sensorToCreate);

        //then
        then(moistureSensorRepository).should(times(1)).save(any(MoistureSensor.class));
    }

    @Test
    public void shouldTryCreateSensorByValues() {
        //given
        MoistureSensor sensorToCreate = sensors().get(0);

        //when
        moistureSensorService.createSensor(sensorToCreate.getName(), sensorToCreate.getChannel());

        //then
        then(moistureSensorRepository).should(times(1)).save(any(MoistureSensor.class));
    }

    @Test
    public void shouldTryUpdateSensor() {
        //given
        MoistureSensor sensorToUpdate = sensors().get(0);

        //when
        moistureSensorService.updateSensor(sensorToUpdate);

        //then
        then(moistureSensorRepository).should(times(1)).updateSensorById(any(String.class), any(int.class), any(Long.class));
    }

    private List<MoistureSensor> sensors() {
        List<MoistureSensor> sensors = new ArrayList<>();
        sensors.add(new MoistureSensor(1L, MOISTURE_SENSOR + " 1", 1));
        sensors.add(new MoistureSensor(2L, MOISTURE_SENSOR + " 2", 2));
        sensors.add(new MoistureSensor(3L, MOISTURE_SENSOR + " 3", 3));

        return sensors;
    }
}
