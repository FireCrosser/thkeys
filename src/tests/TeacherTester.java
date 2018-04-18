package tests;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import dao.DatabaseFiller;
import dao.TeacherDAO;

public class TeacherTester {
	
	@Before
	public void fillDatabase() {
		try {
			DatabaseFiller.fillSubjectsData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTeacherSchedule() {
		TeacherDAO teachDao = new TeacherDAO();
		
		//System.out.println("Розклад викладача доц. A. М. Глибовець");
		Set<Map<String, String>> sched = teachDao.getTeacherSchedule("доц. В. В. Бублик");
		
		for(Map<String, String> sch : sched) {
			for (Map.Entry<String, String> entry : sch.entrySet()) {
		        System.out.println(entry.getKey() + ": " + entry.getValue());
		    }
			
			System.out.println("********************");
		}
	}

}
