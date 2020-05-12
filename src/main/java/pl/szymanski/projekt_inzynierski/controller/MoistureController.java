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
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.MoistureSensorService;

/**
 * REST API MoistureController providing endpoints
 * for moisture sensor management
 */
@RestController
@RequestMapping("api/sensor/moisture")
@RequiredArgsConstructor
public class MoistureController {

    private final MoistureSensorService moistureSensorService;

    /**
     * Provides all moisture sensors
     *
     * @return List with all moisture sensors
     */
    @GetMapping
    @ApiOperation(value = "Returns all available Moisture Sensors",
            notes = "Returns a list of all Moisture Sensors",
            response = MoistureSensor.class,
            responseContainer = "List")
    public List<MoistureSensor> getAll() {
        return moistureSensorService.getAllSensors();
    }

    /**
     * This method is used to add a new moisture sensor
     *
     * @param moistureSensor {@link MoistureSensor}
     */
    @PostMapping
    @ApiOperation(value = "Add a new Moisture Sensor",
            notes = "Use this to add a new Moisture Sensor")
    public void addSensor(@ApiParam(value = "You need to provide MoistureSensor object", required = true)
                          @RequestBody MoistureSensor moistureSensor) {
        moistureSensorService.createSensor(moistureSensor);
    }

    /**
     * Find by id moisture sensor object {@link MoistureSensor}
     *
     * @param id Id of the moisture sensor you want to receive
     * @return Return a single MoistureSensor {@link MoistureSensor} object
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find Moisture Sensor by id",
            notes = "Provide an id to look up specific MoistureSensor from all moisture sensors",
            response = MoistureSensor.class)
    public MoistureSensor getById(@ApiParam(value = "ID value for the moisture sensor you need to retrieve", required = true)
                                  @PathVariable Long id) {
        Optional<MoistureSensor> sensor = moistureSensorService.getSensor(id);
        return sensor.orElse(null);
    }

    /**
     * Allows to update a specific moisture sensor
     *
     * @param id             Id of the moisture sensor you want to update
     * @param moistureSensor Updated moisture sensor
     */
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update Moisture Sensor by id",
            notes = "Provide an id and MoistureSensor object to update up specific MoistureSensor")
    public void updateSensor(@ApiParam(value = "ID value for the moisture sensor you need to update")
                             @PathVariable Long id,
                             @ApiParam(value = "Moisture Sensor object contains updated variables")
                             @RequestBody MoistureSensor moistureSensor) {
        moistureSensor.setId(id);
        moistureSensorService.updateSensor(moistureSensor);
    }

    /**
     * Deletes the moisture sensor
     *
     * @param id Id of the moisture sensor you want to delete
     * @return Returns the result of the delete operation true or false
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete Moisture Sensor by id",
            notes = "Provide an id to delete up specific MoistureSensor")
    public boolean deleteSensor(@ApiParam(value = "ID value for the moisture sensor you need to delete")
                                @PathVariable Long id) {
        Optional<MoistureSensor> moistureSensor = moistureSensorService.getSensor(id);
        if (moistureSensor.isPresent()) {
            moistureSensorService.deleteSensor(moistureSensor.get());
            return true;
        }
        return false;
    }
}
