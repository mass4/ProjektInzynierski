package pl.szymanski.projekt_inzynierski.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TemperatureSensor is the entity represent physical temperature sensor
 * @author Marek Szymański
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "Details about the TemperatureSensor")
@Entity(name = "temperature_sensor")
public class TemperatureSensor {

    @Id
    @ApiModelProperty(notes = "Unique id of the temperature sensor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ApiModelProperty(notes = "Name of the temperature sensor")
    @NotNull(message = "Name is required.")
    private String name;

    /**
     * Address 1-Wire to which the temperature sensor in the device is connected
     */
    @Valid
    @ApiModelProperty(notes = "Address of the temperature sensor")
    @NotNull(message = "Address is required.")
    private String address;

    public TemperatureSensor(@Valid @NotNull(message = "Name is required.") String name, @Valid @NotNull(message = "Address is required.") String address) {
        this.name = name;
        this.address = address;
    }
}
