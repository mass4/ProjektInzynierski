package pl.szymanski.projekt_inzynierski.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * SensorReading collect readings to ArrayList from sensor
 * @author Marek Szymański
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ApiModel(description = "Details about the SensorReading")
public class SensorReading {

    @ApiModelProperty(notes = "Name of the sensor")
    private String name;

    @ApiModelProperty(notes = "SingleReadings list with readings")
    private List<SingleReading> readings;

    public SensorReading() {
        this.readings = new ArrayList<>();
    }

    public SensorReading(String name) {
        this.name = name;
        this.readings = new ArrayList<>();
    }

    /**
     * This method adds a single reading to the list of readings
     *
     * @param singleReading Is a single reading that will be added to the list
     */
    public void addToList(SingleReading singleReading) {
        readings.add(singleReading);
    }
}
