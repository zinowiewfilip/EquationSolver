package pl.kurs.equationsolver.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kurs.equationsolver.config.JpaConfig;
import pl.kurs.equationsolver.models.OperationEvent;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, OperationEventDao.class})
@ComponentScan(basePackages = "pl.kurs.equationsolver")
public class IntegrationDaoTest {
    @Resource
    private OperationEventDao operationEventDao;


    @Test
    public void givenEventWhenSaveThenGet()  {
        String operationForTest = "2 + 2 * 2";
       OperationEvent operationEvent = new OperationEvent(
               Timestamp.from(Instant.now()),
               operationForTest);
       operationEventDao.save(operationEvent);

       OperationEvent testEvent = operationEventDao.get(1l);
       assertEquals("2 + 2 * 2", testEvent.getOperation());

    }

}