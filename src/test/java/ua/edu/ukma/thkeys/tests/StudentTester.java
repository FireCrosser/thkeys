package ua.edu.ukma.thkeys.tests;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import ua.edu.ukma.thkeys.dao.DatabaseFiller;
import ua.edu.ukma.thkeys.dao.StudentDAO;

public class StudentTester {
	
	@Test
	public void getStudentsSpecialities() throws IOException {
		DatabaseFiller.fillSubjectsData();
		
		StudentDAO stDao = new StudentDAO();
		Set<String> specs = stDao.getSpecialities();
		
		for(String sp : specs) {
			System.out.println(sp);
		}
	}

}
