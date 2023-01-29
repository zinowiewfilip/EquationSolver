package pl.kurs.equationsolver.models;

import org.springframework.stereotype.Component;

@Component
public class SubstractionOperator implements Operator{

    @Override
    public char getSymbol() {
        return '-';
    }
}
