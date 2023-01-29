package pl.kurs.equationsolver.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;
import pl.kurs.equationsolver.services.IOperationService;

import java.util.Arrays;
import java.util.Scanner;

@ComponentScan(basePackages = "pl.kurs.equationsolver")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        IOperationService operationService = ctx.getBean(IOperationService.class);

//        Scanner in = new Scanner(System.in);
//        String equation = in.nextLine();

        try {
            System.out.println(operationService.calculate(args[0]));
        } catch (InvalidEquationFormatException | UnknownOperatorException e) {
            e.printStackTrace();
        }

    }

}