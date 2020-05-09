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
@Entity(name = "moisture_sensor")
public class MoistureSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull(message = "Name is required.")
    private String name;

    @Valid
    @NotNull(message = "Channel is required.")
    private int channel;

    public MoistureSensor(@Valid @NotNull(message = "Name is required.") String name, @Valid @NotNull(message = "Channel is required.") int channel) {
        this.name = name;
        this.channel = channel;
    }
}
