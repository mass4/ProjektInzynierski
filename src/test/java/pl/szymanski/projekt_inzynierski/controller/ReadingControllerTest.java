package pl.szymanski.projekt_inzynierski.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import pl.szymanski.projekt_inzynierski.model.SensorReading;
import pl.szymanski.projekt_inzynierski.model.SingleReading;
import pl.szymanski.projekt_inzynierski.service.MoistureReadingService;
import pl.szymanski.projekt_inzynierski.service.TemperatureReadingService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReadingController.class)
@RunWith(SpringRunner.class)
public class ReadingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TemperatureReadingService temperatureReadingService;

    @MockBean
    private MoistureReadingService moistureReadingService;

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN);
    private static final String MOISTURE_SENSOR = "Wilgotnosc - Sonda";
    private static final String TEMPERATURE_SENSOR = "Temperatura - Sonda";
    private static final String API_MOISTURE_SENSOR_READINGS_URI = "/api/readings/moisture/";
    private static final String API_TEMPERATURE_SENSOR_READINGS_URI = "/api/readings/temperature/";

    @Test
    public void shouldReturnTemperatureReadingsBetweenDate() throws Exception {
        //given
        Date startDate = new Date(1L);
        Date endDate = new Date(1000L);
        String startDateAsString = DATE_FORMAT.format(startDate);
        String endDateAsString = DATE_FORMAT.format(endDate);
        given(temperatureReadingService.getSensorReadingsBetween(any(Date.class), any(Date.class))).willReturn(temperatureReadings());

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(API_TEMPERATURE_SENSOR_READINGS_URI + startDateAsString + "/" + endDateAsString)
                .contentType(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("[0].name").value(TEMPERATURE_SENSOR + " 1"))
                .andExpect(jsonPath("[0].readings", hasSize(2)))
                .andExpect(jsonPath("[1].name").value(TEMPERATURE_SENSOR + " 2"))
                .andExpect(jsonPath("[1].readings", hasSize(2)))
                .andExpect(jsonPath("[2].name").value(TEMPERATURE_SENSOR + " 3"))
                .andExpect(jsonPath("[2].readings", hasSize(2)));
        then(temperatureReadingService).should(times(1)).getSensorReadingsBetween(any(Date.class), any(Date.class));
    }

    @Test
    public void shouldReturnMoistureReadingsBetweenDate() throws Exception {
        //given
        Date startDate = new Date(1L);
        Date endDate = new Date(1000L);
        String startDateAsString = DATE_FORMAT.format(startDate);
        String endDateAsString = DATE_FORMAT.format(endDate);
        given(moistureReadingService.getSensorReadingsBetween(any(Date.class), any(Date.class))).willReturn(moistureReadings());

        //when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(API_MOISTURE_SENSOR_READINGS_URI + startDateAsString + "/" + endDateAsString)
                .contentType(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("[0].name").value(MOISTURE_SENSOR + " 1"))
                .andExpect(jsonPath("[0].readings", hasSize(2)))
                .andExpect(jsonPath("[1].name").value(MOISTURE_SENSOR + " 2"))
                .andExpect(jsonPath("[1].readings", hasSize(2)))
                .andExpect(jsonPath("[2].name").value(MOISTURE_SENSOR + " 3"))
                .andExpect(jsonPath("[2].readings", hasSize(2)));
        then(moistureReadingService).should(times(1)).getSensorReadingsBetween(any(Date.class), any(Date.class));
    }

    private List<SensorReading> temperatureReadings() {
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

    private List<SensorReading> moistureReadings() {
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
