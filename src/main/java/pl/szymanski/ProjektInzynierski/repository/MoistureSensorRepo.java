package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.MoistureSensor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class MoistureSensorRepo implements MoistureSensorRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createSensor(String name, int channel) {
        MoistureSensor moistureSensor = new MoistureSensor(name,channel);
        entityManager.persist(moistureSensor);
    }

    @Override
    @Transactional
    public void createSensor(MoistureSensor moistureSensor) {
        entityManager.persist(moistureSensor);
    }

    @Override
    public List<MoistureSensor> getAllSensors() {
        Collection<MoistureSensor> moistureSensors = entityManager.createQuery("from moisturesensor", MoistureSensor.class).getResultList();
        return new ArrayList<>(moistureSensors);
    }

    @Override
    @Transactional
    public void deleteSensor(MoistureSensor moistureSensor) {
        entityManager.remove(moistureSensor);
    }

    @Override
    public MoistureSensor getSensor(Long id) {
        return entityManager.find(MoistureSensor.class,id);
    }

    @Override
    @Transactional
    public void updateSensor(MoistureSensor moistureSensor) {
        entityManager.merge(moistureSensor);
    }
}
