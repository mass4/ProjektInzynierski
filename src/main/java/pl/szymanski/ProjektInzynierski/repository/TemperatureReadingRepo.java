package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.TemperatureReading;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class TemperatureReadingRepo implements TemperatureReadingRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TemperatureReading> getAllReadings() {
        Collection<TemperatureReading> moistureSensors = entityManager.createQuery("from temperaturereading", TemperatureReading.class).getResultList();
        return new ArrayList<>(moistureSensors);
    }
}
