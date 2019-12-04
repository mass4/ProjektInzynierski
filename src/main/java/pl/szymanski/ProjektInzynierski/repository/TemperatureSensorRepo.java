package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class TemperatureSensorRepo implements TemperatureSensorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createSensor(String name, String address) {
        TemperatureSensor temperatureSensor = new TemperatureSensor(name,address);
        entityManager.persist(temperatureSensor);
    }

    @Override
    @Transactional
    public void createSensor(TemperatureSensor temperatureSensor) {
        entityManager.persist(temperatureSensor);
    }

    @Override
    public List<TemperatureSensor> getAllSensors() {
        Collection<TemperatureSensor> temperatureSensors = entityManager.createQuery("from temperaturesensor", TemperatureSensor.class).getResultList();
        return new ArrayList<>(temperatureSensors);
    }

    @Override
    @Transactional
    public void deleteSensor(TemperatureSensor temperatureSensor) {
        entityManager.createQuery("DELETE FROM temperaturereading WHERE sensor_id="+temperatureSensor.getId()).executeUpdate();
        entityManager.remove(temperatureSensor);
    }

    @Override
    public TemperatureSensor getSensor(Long id) {
        return entityManager.find(TemperatureSensor.class,id);
    }

    @Override
    @Transactional
    public void updateSensor(TemperatureSensor temperatureSensor) {
        entityManager.merge(temperatureSensor);
    }
}
