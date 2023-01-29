package pl.kurs.equationsolver.dao;

import pl.kurs.equationsolver.models.OperationEvent;

public interface IOperationEventDao {

    void save(OperationEvent operationEvent);

    OperationEvent get(Long id);
}
