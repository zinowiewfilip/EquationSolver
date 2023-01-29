package pl.kurs.equationsolver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class OperationEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private Timestamp date;

    private String operation;

    public OperationEvent() {
    }

    public OperationEvent(Timestamp date, String operation) {
        this.date = date;
        this.operation = operation;
    }
}
