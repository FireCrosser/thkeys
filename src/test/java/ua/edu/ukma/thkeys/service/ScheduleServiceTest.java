package ua.edu.ukma.thkeys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.ukma.thkeys.ContextTest;

public class ScheduleServiceTest extends ContextTest{

    @Autowired
    public ScheduleService scheduleService;
    
    @Test
    public void testSomeMethod() {
        scheduleService.getScheduleForMethodist();
    }
    
}
