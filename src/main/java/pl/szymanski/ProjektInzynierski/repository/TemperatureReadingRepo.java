package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.SensorReading;
import pl.szymanski.ProjektInzynierski.model.SingleReading;
import pl.szymanski.ProjektInzynierski.model.TemperatureReading;
import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public class TemperatureReadingRepo implements TemperatureReadingRepository{

    @Autowired
    private TemperatureSensorRepo temperatureSensorRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TemperatureReading> getAllReadings() {
        Collection<TemperatureReading> temperatureSensors = entityManager.createQuery("from temperaturereading", TemperatureReading.class).getResultList();
        return new ArrayList<>(temperatureSensors);
    }

    @Override
    public List<TemperatureReading> getReadingsBetween(Date startDate, Date endDate) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start_date = f.format(startDate.getTime());
        String end_date = f.format(endDate.getTime());

        Collection<TemperatureReading> temperatureSensors = entityManager.createQuery("from temperaturereading WHERE time >= '"+start_date+"' AND time <= '"+end_date+"'", TemperatureReading.class).getResultList();
        return new ArrayList<>(temperatureSensors);
    }

    public List<SensorReading> getAllSensorReadings(){
        List<SensorReading> sensorReadings;

        List<TemperatureReading> temperatureReadings = getAllReadings();
        List<TemperatureSensor> temperatureSensors = temperatureSensorRepo.getAllSensors();

        sensorReadings = createSensorReadings(temperatureReadings,temperatureSensors);

        return sensorReadings;
    }

    public List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate){
        List<SensorReading> sensorReadings;

        List<TemperatureReading> temperatureReadings = getReadingsBetween(startDate, endDate);
        List<TemperatureSensor> temperatureSensors = temperatureSensorRepo.getAllSensors();

        sensorReadings = createSensorReadings(temperatureReadings,temperatureSensors);

        return sensorReadings;
    }

    private List<SensorReading> createSensorReadings(List<TemperatureReading> temperatureReadings, List<TemperatureSensor> temperatureSensors){
        List<SensorReading> sensorReadings = new ArrayList<>();

        for (TemperatureSensor temperatureSensor:temperatureSensors) {
            sensorReadings.add(new SensorReading(temperatureSensor.getName()));
        }

        for (TemperatureReading temperatureReading: temperatureReadings){
            for(SensorReading sensorReading:sensorReadings){
                if(temperatureReading.getName().equals(sensorReading.getName())){
                    Float readValue = temperatureReading.getValue();
                    Date readTime = temperatureReading.getTime();

                    SingleReading read = new SingleReading(readValue,readTime);
                    sensorReading.addToList(read);
                    break;
                }
            }
        }

        return  sensorReadings;
    }
}
