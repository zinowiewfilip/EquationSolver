package pl.kurs.equationsolver.services;

import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;

import java.io.IOException;

public interface IInputValidator {
    String validateFormat(String stringToValidate) throws InvalidEquationFormatException, IOException;

    String validateOperators(String stringToValidate) throws UnknownOperatorException, IOException;
}
