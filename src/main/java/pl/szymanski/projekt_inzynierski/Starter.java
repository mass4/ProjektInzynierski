package pl.szymanski.projekt_inzynierski;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.szymanski.projekt_inzynierski.model.MoistureSensor;
import pl.szymanski.projekt_inzynierski.model.TemperatureSensor;
import java.util.List;
import pl.szymanski.projekt_inzynierski.service.MoistureSensorService;
import pl.szymanski.projekt_inzynierski.service.TemperatureSensorService;

@Component
@RequiredArgsConstructor
public class Starter implements CommandLineRunner {

    private final TemperatureSensorService temperatureSensorService;
    private final MoistureSensorService moistureSensorService;

    //Temperature sensors connected to the physical device (Name | 1-Wire Address)
    private static final TemperatureSensor TEMPERATURE_SENSOR_OUTSIDE = new TemperatureSensor("Temperatura na zewnatrz", "28-7661981864ff");
    private static final TemperatureSensor TEMPERATURE_SENSOR_0 = new TemperatureSensor("Temperatura - Sonda 0", "28-011452ed36aa");
    private static final TemperatureSensor TEMPERATURE_SENSOR_1 = new TemperatureSensor("Temperatura - Sonda 1", "28-0114531550aa");
    private static final TemperatureSensor TEMPERATURE_SENSOR_2 = new TemperatureSensor("Temperatura - Sonda 2", "28-011452f02caa");
    private static final TemperatureSensor TEMPERATURE_SENSOR_3 = new TemperatureSensor("Temperatura - Sonda 3", "28-011452e3c2aa");

    //Moisture sensors connected to the physical device (Name | Channel)
    private static final MoistureSensor MOISTURE_SENSOR_0 = new MoistureSensor("Wilgotnosc - Sonda 0", 0);
    private static final MoistureSensor MOISTURE_SENSOR_1 = new MoistureSensor("Wilgotnosc - Sonda 1", 1);
    private static final MoistureSensor MOISTURE_SENSOR_2 = new MoistureSensor("Wilgotnosc - Sonda 2", 2);
    private static final MoistureSensor MOISTURE_SENSOR_3 = new MoistureSensor("Wilgotnosc - Sonda 3", 3);

    @Override
    public void run(String... args) throws Exception {
        List<TemperatureSensor> temperatureSensors = temperatureSensorService.getAllSensors();
        if (temperatureSensors.isEmpty()) {
            temperatureSensorService.createSensor(TEMPERATURE_SENSOR_OUTSIDE);
            temperatureSensorService.createSensor(TEMPERATURE_SENSOR_0);
            temperatureSensorService.createSensor(TEMPERATURE_SENSOR_1);
            temperatureSensorService.createSensor(TEMPERATURE_SENSOR_2);
            temperatureSensorService.createSensor(TEMPERATURE_SENSOR_3);
        }

        List<MoistureSensor> moistureSensors = moistureSensorService.getAllSensors();
        if (moistureSensors.isEmpty()) {
            moistureSensorService.createSensor(MOISTURE_SENSOR_0);
            moistureSensorService.createSensor(MOISTURE_SENSOR_1);
            moistureSensorService.createSensor(MOISTURE_SENSOR_2);
            moistureSensorService.createSensor(MOISTURE_SENSOR_3);
        }
    }
}
