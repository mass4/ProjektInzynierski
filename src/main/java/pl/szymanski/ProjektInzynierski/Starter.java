package pl.szymanski.ProjektInzynierski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.szymanski.ProjektInzynierski.model.MoistureSensor;
import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;
import pl.szymanski.ProjektInzynierski.repository.MoistureSensorRepo;
import pl.szymanski.ProjektInzynierski.repository.TemperatureSensorRepo;

import java.util.List;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    TemperatureSensorRepo temperatureSensorRepo;

    @Autowired
    MoistureSensorRepo moistureSensorRepo;

    @Override
    public void run(String... strings) throws Exception{

        List<TemperatureSensor> temperatureSensors = temperatureSensorRepo.getAllSensors();
        if (temperatureSensors.size() == 0){
            temperatureSensorRepo.createSensor("Temperatura na zewnątrz","28-7661981864ff");
            temperatureSensorRepo.createSensor("Temperatura - Sonda 0","28-011452ed36aa");
            temperatureSensorRepo.createSensor("Temperatura - Sonda 1","28-0114531550aa");
            temperatureSensorRepo.createSensor("Temperatura - Sonda 2","28-011452f02caa");
            temperatureSensorRepo.createSensor("Temperatura - Sonda 3","28-011452e3c2aa");
        }

        List<MoistureSensor> moistureSensors = moistureSensorRepo.getAllSensors();
        if(moistureSensors.size() == 0){
            moistureSensorRepo.createSensor("Wilgotność - Sonda 0",0);
            moistureSensorRepo.createSensor("Wilgotność - Sonda 1",1);
            moistureSensorRepo.createSensor("Wilgotność - Sonda 2",2);
            moistureSensorRepo.createSensor("Wilgotność - Sonda 3",3);
        }

    }
}
