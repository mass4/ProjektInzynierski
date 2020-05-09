package pl.szymanski.projekt_inzynierski.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SensorReading {
    private String name;
    private List<SingleReading> readings;

    public SensorReading() {
        this.readings = new ArrayList<>();
    }

    public SensorReading(String name) {
        this.name = name;
        this.readings = new ArrayList<>();
    }

    public void addToList(SingleReading singleReading) {
        readings.add(singleReading);
    }
}
