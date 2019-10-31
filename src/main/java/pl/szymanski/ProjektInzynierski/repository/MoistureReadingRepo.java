package pl.szymanski.ProjektInzynierski.repository;

import org.springframework.stereotype.Repository;
import pl.szymanski.ProjektInzynierski.model.MoistureReading;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class MoistureReadingRepo implements MoistureReadingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MoistureReading> getAllReadings() {
        Collection<MoistureReading> moistureSensors = entityManager.createQuery("from moisturereading", MoistureReading.class).getResultList();
        return new ArrayList<>(moistureSensors);
    }
}
