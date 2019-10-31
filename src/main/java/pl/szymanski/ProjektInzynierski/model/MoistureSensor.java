package pl.szymanski.ProjektInzynierski.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "moisturesensor")
public class MoistureSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Channel is required.")
    private int channel;

    public MoistureSensor() {
    }

    public MoistureSensor(@NotNull(message = "Name is required.") String name, @NotNull(message = "Channel is required.") int channel) {
        this.name = name;
        this.channel = channel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
