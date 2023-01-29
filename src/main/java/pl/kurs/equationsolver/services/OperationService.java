package pl.kurs.equationsolver.services;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import pl.kurs.equationsolver.dao.IOperationEventDao;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.models.OperationEvent;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class OperationService implements IOperationService {

    private IOperationEventDao operationEventDao;
    private IInputValidator inputValidator;

    public OperationService(IOperationEventDao operationEventDao, IInputValidator inputValidator) {
        this.operationEventDao = operationEventDao;
        this.inputValidator = inputValidator;
    }

    @Override
    public double calculate(String operation) throws InvalidEquationFormatException, UnknownOperatorException {

        try {
            inputValidator.validateFormat(operation);
        }catch (IOException e) {
            throw new InvalidEquationFormatException("Invalid equation format");
        }
        try {
            inputValidator.validateOperators(operation);
        } catch (IOException e) {
            throw new UnknownOperatorException("Unknown operator in equation");
        }


        Expression expression = new ExpressionBuilder(operation).build();
        OperationEvent operationEvent = new OperationEvent(Timestamp.from(Instant.now()), operation);
        operationEventDao.save(operationEvent);
        return expression.evaluate();
    }
}
