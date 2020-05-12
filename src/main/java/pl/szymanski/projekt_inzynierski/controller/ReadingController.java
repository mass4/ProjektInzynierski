package pl.szymanski.projekt_inzynierski.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymanski.projekt_inzynierski.model.SensorReading;
import java.util.Date;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.MoistureReadingService;
import pl.szymanski.projekt_inzynierski.service.TemperatureReadingService;

/**
 * REST API ReadingController providing endpoints
 * for managing sensor reading
 */
@RestController
@RequestMapping("api/readings")
@RequiredArgsConstructor
public class ReadingController {

    private final TemperatureReadingService temperatureReadingService;
    private final MoistureReadingService moistureReadingService;

    /**
     * Provides a list of temperature sensor readings for the selected date range
     *
     * @param startDate Date from which the readings are to be found
     * @param endDate   Date by which the searched readings are to be completed
     * @return Returns all readings from temperature sensors between dates
     */
    @GetMapping(value = "/temperature/{startDate}/{endDate}")
    @ApiOperation(value = "Returns all readings from temperature sensors between dates",
            notes = "Returns a list of all readings from temperature sensors between two dates which you provide",
            response = SensorReading.class,
            responseContainer = "List")
    public List<SensorReading> getTemperatureBetween(@ApiParam(value = "You need to provide start Date", required = true)
                                                     @PathVariable("startDate") Date startDate,
                                                     @ApiParam(value = "You need to provide end Date", required = true)
                                                     @PathVariable("endDate") Date endDate) {
        return temperatureReadingService.getSensorReadingsBetween(startDate, endDate);
    }

    /**
     * Provides a list of temperature sensor readings for the selected date range
     *
     * @param startDate Date from which the readings are to be found
     * @param endDate   Date by which the searched readings are to be completed
     * @return Returns all readings from moisture sensors between dates
     */
    @GetMapping(value = "/moisture/{startDate}/{endDate}")
    @ApiOperation(value = "Returns all readings from moisture sensors between dates",
            notes = "Returns a list of all readings from moisture sensors between two dates which you provide",
            response = SensorReading.class,
            responseContainer = "List")
    public List<SensorReading> getMoistureBetween(@ApiParam(value = "You need to provide start Date", required = true)
                                                  @PathVariable("startDate") Date startDate,
                                                  @ApiParam(value = "You need to provide end Date", required = true)
                                                  @PathVariable("endDate") Date endDate) {
        return moistureReadingService.getSensorReadingsBetween(startDate, endDate);
    }
}
