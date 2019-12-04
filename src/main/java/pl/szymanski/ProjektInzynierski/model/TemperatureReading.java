package pl.szymanski.ProjektInzynierski.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "temperaturereading")
public class TemperatureReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

    private float value;

    @OneToOne
    private TemperatureSensor sensor;

    public TemperatureReading() {
    }

    public TemperatureReading(Date time, float value, TemperatureSensor sensor) {
        this.time = time;
        this.value = value;
        this.sensor = sensor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public TemperatureSensor getSensor() {
        return sensor;
    }

    public void setSensor(TemperatureSensor sensor) {
        this.sensor = sensor;
    }
}
