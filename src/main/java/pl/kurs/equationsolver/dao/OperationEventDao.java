package pl.kurs.equationsolver.dao;

import org.springframework.stereotype.Repository;
import pl.kurs.equationsolver.models.OperationEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class OperationEventDao implements IOperationEventDao {

    @PersistenceUnit
    private EntityManagerFactory factory;

    @Override
    public void save(OperationEvent operationEvent) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(operationEvent);
        tx.commit();
        entityManager.close();
    }

    @Override
    public OperationEvent get(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        OperationEvent event = entityManager.find(OperationEvent.class, id);
        entityManager.close();
        return event;
    }
}
