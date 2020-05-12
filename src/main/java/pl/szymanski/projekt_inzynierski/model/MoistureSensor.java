package pl.szymanski.projekt_inzynierski.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MoistureSensor is the entity represent physical moisture sensor
 */
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "Details about the MoistureSensor")
@Entity(name = "moisture_sensor")
public class MoistureSensor {

    @Id
    @ApiModelProperty(notes = "Unique id of the moisture sensor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ApiModelProperty(notes = "Name of the moisture sensor")
    @NotNull(message = "Name is required.")
    private String name;

    /**
     * Channel to which the moisture sensor in the device is connected
     */
    @Valid
    @ApiModelProperty(notes = "Channel of the moisture sensor")
    @NotNull(message = "Channel is required.")
    private int channel;

    public MoistureSensor(@Valid @NotNull(message = "Name is required.") String name, @Valid @NotNull(message = "Channel is required.") int channel) {
        this.name = name;
        this.channel = channel;
    }
}
