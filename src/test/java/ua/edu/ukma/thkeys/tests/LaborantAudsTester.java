package ua.edu.ukma.thkeys.tests;

import java.io.IOException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.ukma.thkeys.ContextTest;

import ua.edu.ukma.thkeys.dao.ClassroomDAO;
import ua.edu.ukma.thkeys.dao.DatabaseFiller;

public class LaborantAudsTester extends ContextTest{
	
    @Autowired
    public DatabaseFiller databaseFiller;
    
    @Autowired
    public ClassroomDAO clDao;
    
	@Before
	public void fillData() throws IOException {
		databaseFiller.fillClassroomsData();
	}
	
	@Test
	public void auditoriesTest() {
		
		System.out.println("All auditories:");
		Set<String> audsAll = clDao.getAllClassrooms();
		for(String cl : audsAll) {
			System.out.print(cl+" ");
		}
		
		System.out.println();
		System.out.println("******");
		
		System.out.println("Auditories of 1 campus:");
		Set<String> audsFirst = clDao.getClassroomsOfTheCampus(1);
		for(String cl : audsFirst) {
			System.out.print(cl+" ");
		}
		
		System.out.println();
		System.out.println("******");
		
		System.out.println("Auditories with computers:");
		Set<String> audsComp = clDao.getComputerClassrooms();
		for(String cl : audsComp) {
			System.out.print(cl+" ");
		}
		
	}

}
