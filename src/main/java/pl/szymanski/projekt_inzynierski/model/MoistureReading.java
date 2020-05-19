package pl.szymanski.projekt_inzynierski.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MoistureReading entity collect read from the moisture sensor
 * @author Marek Szymański
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "moisture_reading")
public class MoistureReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private float value;

    @OneToOne
    private MoistureSensor sensor;

    public MoistureReading(Date time, float value, MoistureSensor sensor) {
        this.time = time;
        this.value = value;
        this.sensor = sensor;
    }
}
