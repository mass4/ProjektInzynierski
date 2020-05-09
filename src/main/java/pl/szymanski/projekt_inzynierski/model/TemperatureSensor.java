package pl.szymanski.projekt_inzynierski.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "temperature_sensor")
public class TemperatureSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull(message = "Name is required.")
    private String name;

    @Valid
    @NotNull(message = "Address is required.")
    private String address;

    public TemperatureSensor(@Valid @NotNull(message = "Name is required.") String name, @Valid @NotNull(message = "Address is required.") String address) {
        this.name = name;
        this.address = address;
    }
}
