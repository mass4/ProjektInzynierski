package pl.szymanski.ProjektInzynierski.model;

import java.util.Date;

public class SingleReading {
    private Float value;
    private Date timestamp;

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
