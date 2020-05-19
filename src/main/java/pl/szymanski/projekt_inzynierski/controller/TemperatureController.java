package pl.szymanski.projekt_inzynierski.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.TemperatureSensorService;

/**
 * REST API TemperatureController providing endpoints
 * for temperature sensor management
 * @author Marek Szymański
 */
@RestController
@RequestMapping("api/sensor/temperature")
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureSensorService temperatureSensorService;

    /**
     * Provides all temperature sensors
     *
     * @return List with all temperature sensors
     */
    @GetMapping
    @ApiOperation(value = "Returns all available Temperature Sensors",
            notes = "Returns a list of all Temperature Sensors",
            response = TemperatureSensor.class,
            responseContainer = "List")
    public List<TemperatureSensor> getAll() {
        return temperatureSensorService.getAllSensors();
    }

    /**
     * This method is used to add a new temperature sensor
     *
     * @param temperatureSensor {@link TemperatureSensor}
     */
    @PostMapping
    @ApiOperation(value = "Add a new Temperature Sensor",
            notes = "Use this to add a new Temperature Sensor")
    public void addSensor(@ApiParam(value = "You need to provide TemperatureSensor object", required = true)
                          @RequestBody TemperatureSensor temperatureSensor) {
        temperatureSensorService.createSensor(temperatureSensor);
    }

    /**
     * Find by id temperature sensor object {@link TemperatureSensor}
     *
     * @param id Id of the temperature sensor you want to receive
     * @return Return a single TemperatureSensor {@link TemperatureSensor} object
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find Temperature Sensor by id",
            notes = "Provide an id to look up specific TemperatureSensor from all temperature sensors",
            response = TemperatureSensor.class)
    public TemperatureSensor getById(@ApiParam(value = "ID value for the temperature sensor you need to retrieve", required = true)
                                     @PathVariable Long id) {
        Optional<TemperatureSensor> sensor = temperatureSensorService.getSensor(id);
        return sensor.orElse(null);
    }

    /**
     * Allows to update a specific temperature sensor
     *
     * @param id                Id of the temperature sensor you want to update
     * @param temperatureSensor Updated temperature sensor
     */
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update Temperature Sensor by id",
            notes = "Provide an id and TemperatureSensor object to update up specific TemperatureSensor")
    public void updateSensor(@ApiParam(value = "ID value for the temperature sensor you need to update")
                             @PathVariable Long id,
                             @ApiParam(value = "Temperature Sensor object contains updated variables")
                             @RequestBody TemperatureSensor temperatureSensor) {
        temperatureSensor.setId(id);
        temperatureSensorService.updateSensor(temperatureSensor);
    }

    /**
     * Deletes the temperature sensor
     *
     * @param id Id of the temperature sensor you want to update
     * @return Returns the result of the delete operation true or false
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete Temperature Sensor by id",
            notes = "Provide an id to delete up specific TemperatureSensor")
    public boolean deleteSensor(@ApiParam(value = "ID value for the temperature sensor you need to delete")
                                @PathVariable Long id) {
        Optional<TemperatureSensor> moistureSensor = temperatureSensorService.getSensor(id);
        if (moistureSensor.isPresent()) {
            temperatureSensorService.deleteSensor(moistureSensor.get());
            return true;
        }
        return false;
    }
}
