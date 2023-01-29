package pl.kurs.equationsolver.services;

import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;

public interface IOperationService {
    double calculate(String operation) throws InvalidEquationFormatException, UnknownOperatorException;
}
