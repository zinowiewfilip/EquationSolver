package pl.kurs.equationsolver.services;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolver.models.AdditionOperator;
import pl.kurs.equationsolver.models.DivisionOperator;
import pl.kurs.equationsolver.models.MultiplicationOperator;
import pl.kurs.equationsolver.models.SubstractionOperator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InputValidator implements IInputValidator {
    List<Character> list = List.of(
            new AdditionOperator().getSymbol(),
            new SubstractionOperator().getSymbol(),
            new DivisionOperator().getSymbol(),
            new MultiplicationOperator().getSymbol());

    public String validateFormat(String stringToValidate) throws IOException {
        char[] arrayFromString = stringToValidate.toCharArray();
        String withoutSpaces = stringToValidate.replaceAll("\\s", "");

        if (arrayFromString.length - withoutSpaces.length() == withoutSpaces.length() - 1)
            return stringToValidate;
        else
            throw new IOException("Invalid format");
    }

    @Override
    public String validateOperators(String stringToValidate) throws IOException {
        String preparedString = stringToValidate
                .replaceAll("[0-9]", "")
                .replaceAll("\\s", "");
        List<Character> chars = preparedString.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
        if (list.containsAll(chars))
            return stringToValidate;
        else
            throw new IOException("Invalid operator");
    }
}


