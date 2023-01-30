package pl.kurs.equationsolver.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kurs.equationsolver.config.JpaConfig;
import pl.kurs.equationsolver.dao.IOperationEventDao;
import pl.kurs.equationsolver.dao.OperationEventDao;
import pl.kurs.equationsolver.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolver.exceptions.UnknownOperatorException;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, InputValidator.class, OperationEventDao.class})
public class OperationServiceTest {

    OperationService operationService;
    @Resource
    private InputValidator inputValidator;
    @Resource
    private OperationEventDao operationEventDao;


    

    @Before
    public void setUp() throws Exception {
        operationService = new OperationService(operationEventDao, inputValidator);
    }

    @Test
    public void shouldCalculateAndReturn6() throws InvalidEquationFormatException, UnknownOperatorException {

        //given
        String equationForTest = "2 + 2 * 2";
        double resultForTest = 6;

        //when
        double result = operationService.calculate(equationForTest);

        //then
        assertTrue(resultForTest == result);
    }
    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenInvalidFormat() {
        //given
        String equationForTest = "2 + 2 *2";

        //when
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> operationService.calculate(equationForTest));

        //then
        assertEquals(InvalidEquationFormatException.class, e.getClass());
    }
    @Test
    public void shouldThrowUnknownOperatorExceptionWhenInvalidOperator() {
        //given
        String equationForTest = "2 + 2 ^ 2";

        //when
        Exception e = assertThrows(UnknownOperatorException.class, () -> operationService.calculate(equationForTest));

        //then
        assertEquals(UnknownOperatorException.class, e.getClass());
    }



}