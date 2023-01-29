package pl.kurs.equationsolver.models;

import org.springframework.stereotype.Component;

@Component
public class AdditionOperator implements Operator {


    public char getSymbol() {
        return '+';
    }
}
