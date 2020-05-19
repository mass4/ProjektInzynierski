package pl.szymanski.projekt_inzynierski.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SingleReading entity is used to record the reading from the sensor
 * @author Marek Szymański
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ApiModel(description = "Details about the SingleReading")
public class SingleReading {

    @ApiModelProperty(notes = "Sensor reading value")
    private float value;

    @ApiModelProperty(notes = "Timestamp of the sensor reading")
    private Date timestamp;
}
