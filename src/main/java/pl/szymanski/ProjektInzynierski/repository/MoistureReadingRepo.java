package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.MoistureReading;
import pl.szymanski.ProjektInzynierski.model.MoistureSensor;
import pl.szymanski.ProjektInzynierski.model.SensorReading;
import pl.szymanski.ProjektInzynierski.model.SingleReading;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public class MoistureReadingRepo implements MoistureReadingRepository {

    @Autowired
    private MoistureSensorRepo moistureSensorRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MoistureReading> getAllReadings() {
        Collection<MoistureReading> moistureSensors = entityManager.createQuery("from moisturereading", MoistureReading.class).getResultList();
        return new ArrayList<>(moistureSensors);
    }

    @Override
    public List<MoistureReading> getReadingsBetween(Date startDate, Date endDate) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start_date = f.format(startDate.getTime());
        String end_date = f.format(endDate.getTime());

        Collection<MoistureReading> moistureSensors = entityManager.createQuery("from moisturereading WHERE time >= '"+start_date+"' AND time <= '"+end_date+"'", MoistureReading.class).getResultList();
        return new ArrayList<>(moistureSensors);
    }

    public List<SensorReading> getAllSensorReadings(){
        List<SensorReading> sensorReadings;

        List<MoistureReading> moistureReadings = getAllReadings();
        List<MoistureSensor> moistureSensors = moistureSensorRepo.getAllSensors();

        sensorReadings = createSensorReadings(moistureReadings,moistureSensors);

        return sensorReadings;
    }

    public List<SensorReading> getSensorReadingsBetween(Date startDate, Date endDate){
        List<SensorReading> sensorReadings;

        List<MoistureReading> moistureReadings = getReadingsBetween(startDate, endDate);
        List<MoistureSensor> moistureSensors = moistureSensorRepo.getAllSensors();

        sensorReadings = createSensorReadings(moistureReadings,moistureSensors);

        return sensorReadings;
    }

    private List<SensorReading> createSensorReadings(List<MoistureReading> moistureReadings, List<MoistureSensor> moistureSensors){
        List<SensorReading> sensorReadings = new ArrayList<>();

        for (MoistureSensor moistureSensor:moistureSensors) {
            sensorReadings.add(new SensorReading(moistureSensor.getName()));
        }

        for (MoistureReading moistureReading: moistureReadings){
            for(SensorReading sensorReading:sensorReadings){
                if(moistureReading.getName().equals(sensorReading.getName())){
                    Float readValue = moistureReading.getValue();
                    Date readTime = moistureReading.getTime();

                    SingleReading read = new SingleReading(readValue,readTime);
                    sensorReading.addToList(read);
                    break;
                }
            }
        }

        return  sensorReadings;
    }
}
