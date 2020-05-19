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
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.service.MoistureSensorService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MoistureController.class)
@RunWith(SpringRunner.class)
public class MoistureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MoistureSensorService moistureSensorService;

    private static final String MOISTURE_SENSOR = "Wilgotnosc - Sonda";
    private static final String API_SENSOR_URI = "/api/sensor/moisture";

    @Test
    public void shouldReturnAllMoistureSensors() throws Exception {
        //given
        given(moistureSensorService.getAllSensors()).willReturn(sensors());

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(API_SENSOR_URI)
                .contentType(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value(MOISTURE_SENSOR + " 1"))
                .andExpect(jsonPath("$[0].channel").value(1))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value(MOISTURE_SENSOR + " 2"))
                .andExpect(jsonPath("$[1].channel").value(2))
                .andExpect(jsonPath("$[2].id").value("3"))
                .andExpect(jsonPath("$[2].name").value(MOISTURE_SENSOR + " 3"))
                .andExpect(jsonPath("$[2].channel").value(3));
        then(moistureSensorService).should(times(1)).getAllSensors();
    }

    @Test
    public void shouldTryAddMoistureSensor() throws Exception {
        //given
        MoistureSensor sensor = new MoistureSensor(1L, MOISTURE_SENSOR, 1);

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(API_SENSOR_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()).content(this.mapper.writeValueAsBytes(sensor));

        //then
        mockMvc.perform(builder).andExpect(status().isOk());
        then(moistureSensorService).should(times(1)).createSensor(any(MoistureSensor.class));
    }

    @Test
    public void shouldReturnMoistureSensorById() throws Exception {
        //given
        MoistureSensor sensor = new MoistureSensor(1L, MOISTURE_SENSOR, 1);
        given(moistureSensorService.getSensor(1L)).willReturn(Optional.of(sensor));

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(API_SENSOR_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
        then(moistureSensorService).should(times(1)).getSensor(any(Long.class));
    }

    @Test
    public void shouldTryUpdateMoistureSensorById() throws Exception {
        //given
        MoistureSensor sensor = new MoistureSensor(1L, MOISTURE_SENSOR, 1);

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(API_SENSOR_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()).content(this.mapper.writeValueAsBytes(sensor));

        //then
        mockMvc.perform(builder).andExpect(status().isOk());
        then(moistureSensorService).should(times(1)).updateSensor(any(MoistureSensor.class));
    }

    @Test
    public void shouldTryDeleteMoistureSensorById() throws Exception {
        //given
        MoistureSensor sensor = new MoistureSensor(1L, MOISTURE_SENSOR, 1);
        given(moistureSensorService.getSensor(1L)).willReturn(Optional.of(sensor));

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(API_SENSOR_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        //then
        mockMvc.perform(builder).andExpect(status().isOk());
        then(moistureSensorService).should(times(1)).deleteSensor(any(MoistureSensor.class));
        then(moistureSensorService).should(times(1)).getSensor(any(Long.class));
    }

    private List<MoistureSensor> sensors() {
        List<MoistureSensor> sensors = new ArrayList<>();
        sensors.add(new MoistureSensor(1L, MOISTURE_SENSOR + " 1", 1));
        sensors.add(new MoistureSensor(2L, MOISTURE_SENSOR + " 2", 2));
        sensors.add(new MoistureSensor(3L, MOISTURE_SENSOR + " 3", 3));
        return sensors;
    }
}
