package pl.szymanski.projekt_inzynierski;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.MoistureSensorService;
import pl.szymanski.projekt_inzynierski.service.TemperatureSensorService;

/**
 * A class used to initialize a database with a list of sensors connected to a physical device
 */
@Component
@RequiredArgsConstructor
public class Starter implements CommandLineRunner {

    private final TemperatureSensorService temperatureSensorService;
    private final MoistureSensorService moistureSensorService;
    private static final String TEMPERATURE_SENSOR = "Temperatura - Sonda";
    private static final String MOISTURE_SENSOR = "Wilgotnosc - Sonda";
    private static final int numbersOfMoistureSensors = 4;

    @Override
    public void run(String... args) throws Exception {
        List<TemperatureSensor> temperatureSensors = temperatureSensorService.getAllSensors();
        if (temperatureSensors.isEmpty()) {
            //Temperature sensors connected to the physical device (Name | 1-Wire Address)
            temperatureSensorService.createSensor(new TemperatureSensor("Temperatura na zewnatrz", "28-7661981864ff"));
            temperatureSensorService.createSensor(new TemperatureSensor(TEMPERATURE_SENSOR + " 0", "28-011452ed36aa"));
            temperatureSensorService.createSensor(new TemperatureSensor(TEMPERATURE_SENSOR + " 1", "28-0114531550aa"));
            temperatureSensorService.createSensor(new TemperatureSensor(TEMPERATURE_SENSOR + " 2", "28-011452f02caa"));
            temperatureSensorService.createSensor(new TemperatureSensor(TEMPERATURE_SENSOR + " 3", "28-011452e3c2aa"));
        }


        List<MoistureSensor> moistureSensors = moistureSensorService.getAllSensors();
        if (moistureSensors.isEmpty()) {
            for (int i = 0; i < numbersOfMoistureSensors; i++) {
                //Moisture sensors connected to the physical device (Name | Channel)
                moistureSensorService.createSensor(new MoistureSensor(MOISTURE_SENSOR + " " + i, i));
            }
        }
    }
}
