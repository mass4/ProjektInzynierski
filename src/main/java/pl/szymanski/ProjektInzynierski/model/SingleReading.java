package pl.szymanski.ProjektInzynierski.model;

import java.util.Date;

public class SingleReading {
    Float value;
    Date timestamp;

    public SingleReading() {
    }

    public SingleReading(Float value, Date timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
