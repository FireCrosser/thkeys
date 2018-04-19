package tests;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import dao.DatabaseFiller;
import dao.StudentDAO;

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
