package pl.szymanski.projekt_inzynierski.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import pl.szymanski.projekt_inzynierski.service.TemperatureSensorService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TemperatureController.class)
@RunWith(SpringRunner.class)
public class TemperatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private TemperatureSensorService temperatureSensorService;

    private static final String TEMPERATURE_SENSOR = "Temperatura - Sonda";
    private static final String API_SENSOR_URI = "/api/sensor/temperature";

    @Test
    public void shouldReturnAllTemperatureSensors() throws Exception {
        //given
        given(temperatureSensorService.getAllSensors()).willReturn(sensors());

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(API_SENSOR_URI)
                .contentType(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value(TEMPERATURE_SENSOR + " 1"))
                .andExpect(jsonPath("$[0].address").value("10-0000000"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value(TEMPERATURE_SENSOR + " 2"))
                .andExpect(jsonPath("$[1].address").value("20-0000000"))
                .andExpect(jsonPath("$[2].id").value("3"))
                .andExpect(jsonPath("$[2].name").value(TEMPERATURE_SENSOR + " 3"))
                .andExpect(jsonPath("$[2].address").value("30-0000000"));
        then(temperatureSensorService).should(times(1)).getAllSensors();
    }

    @Test
    public void shouldTryAddTemperatureSensor() throws Exception {
        //given
        TemperatureSensor sensor = new TemperatureSensor(1L, TEMPERATURE_SENSOR, "00-0000000");

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(API_SENSOR_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()).content(this.mapper.writeValueAsBytes(sensor));

        //then
        mockMvc.perform(builder).andExpect(status().isOk());
        then(temperatureSensorService).should(times(1)).createSensor(any(TemperatureSensor.class));
    }

    @Test
    public void shouldReturnTemperatureSensorById() throws Exception {
        //given
        TemperatureSensor sensor = new TemperatureSensor(1L, TEMPERATURE_SENSOR, "00-0000000");
        given(temperatureSensorService.getSensor(1L)).willReturn(Optional.of(sensor));

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(API_SENSOR_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
        then(temperatureSensorService).should(times(1)).getSensor(any(Long.class));
    }

    @Test
    public void shouldTryUpdateTemperatureSensorById() throws Exception {
        //given
        TemperatureSensor sensor = new TemperatureSensor(1L, TEMPERATURE_SENSOR, "00-0000000");

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(API_SENSOR_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()).content(this.mapper.writeValueAsBytes(sensor));

        //then
        mockMvc.perform(builder).andExpect(status().isOk());
        then(temperatureSensorService).should(times(1)).updateSensor(any(TemperatureSensor.class));
    }

    @Test
    public void shouldTryDeleteTemperatureSensorById() throws Exception {
        //given
        TemperatureSensor sensor = new TemperatureSensor(1L, TEMPERATURE_SENSOR, "00-0000000");
        given(temperatureSensorService.getSensor(1L)).willReturn(Optional.of(sensor));

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(API_SENSOR_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        //then
        mockMvc.perform(builder).andExpect(status().isOk());
        then(temperatureSensorService).should(times(1)).deleteSensor(any(TemperatureSensor.class));
        then(temperatureSensorService).should(times(1)).getSensor(any(Long.class));
    }

    private List<TemperatureSensor> sensors() {
        List<TemperatureSensor> sensors = new ArrayList<>();
        sensors.add(new TemperatureSensor(1L, TEMPERATURE_SENSOR + " 1", "10-0000000"));
        sensors.add(new TemperatureSensor(2L, TEMPERATURE_SENSOR + " 2", "20-0000000"));
        sensors.add(new TemperatureSensor(3L, TEMPERATURE_SENSOR + " 3", "30-0000000"));
        return sensors;
    }
}
