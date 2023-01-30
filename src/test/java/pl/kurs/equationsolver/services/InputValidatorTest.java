package pl.kurs.equationsolver.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kurs.equationsolver.config.JpaConfig;
import pl.kurs.equationsolver.dao.OperationEventDao;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class})
public class InputValidatorTest {

    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {
        inputValidator = new InputValidator();
    }

    @Test
    public void shouldThrowIOExceptionWhenInvalidFormat() {
        //given
        String equationForTest = "2 + 2 *2";

        //when
        Exception e = assertThrows(IOException.class, () -> inputValidator.validateFormat(equationForTest));

        //then
        assertEquals(IOException.class, e.getClass());
    }

    @Test
    public void shouldThrowIOExceptionWhenInvalidOperator() {
        //given
        String equationForTest = "2 + 2 ^ 2";

        //when
        Exception e = assertThrows(IOException.class, () -> inputValidator.validateOperators(equationForTest));

        //then
        assertEquals(IOException.class, e.getClass());
    }
}