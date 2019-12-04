package pl.szymanski.ProjektInzynierski.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "moisturereading")
public class MoistureReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

    private float value;

    @OneToOne
    MoistureSensor sensor;

    public MoistureReading() {
    }

    public MoistureReading(Date time, float value, MoistureSensor sensor) {
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

    public MoistureSensor getSensor() {
        return sensor;
    }

    public void setSensor(MoistureSensor sensor) {
        this.sensor = sensor;
    }
}
