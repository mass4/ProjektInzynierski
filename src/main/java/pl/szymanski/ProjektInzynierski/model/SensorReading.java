package pl.szymanski.ProjektInzynierski.model;

import java.util.ArrayList;
import java.util.List;

public class SensorReading {
    private String name;
    private List<SingleReading> readings;

    public SensorReading() {
        this.readings = new ArrayList<>();
    }

    public SensorReading(String name) {
        this.name=name;
        this.readings = new ArrayList<>();
    }

    public SensorReading(String name, List<SingleReading> singleReadings) {
        this.name = name;
        this.readings = new ArrayList<>();
        this.readings = singleReadings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SingleReading> getReadings() {
        return readings;
    }

    public void setReadings(List<SingleReading> readings) {
        this.readings = readings;
    }

    public void addToList(SingleReading singleReading){
        readings.add(singleReading);
    }
}
