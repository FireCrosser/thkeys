package tests;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import dao.DatabaseFiller;
import dao.TeacherDAO;

public class TeacherTester {
	
	/*@Test
	public void getTeacherSchedule() throws IOException {
		DatabaseFiller.fillSubjectsData();
	
		TeacherDAO teachDao = new TeacherDAO();
		
		//System.out.println("Розклад викладача доц. A. М. Глибовець");
		Set<Map<String, String>> sched = teachDao.getTeacherSchedule("доц. А.М. Глибовець");
		
		for(Map<String, String> sch : sched) {
			for (Map.Entry<String, String> entry : sch.entrySet()) {
		        System.out.println(entry.getKey() + ": " + entry.getValue());
		    }
			
			System.out.println("********************");
		}
	}*/
	
	/*@Test
	public void getTeacherNames() throws IOException {
		DatabaseFiller.fillSubjectsData();
		
		TeacherDAO teachDao = new TeacherDAO();
		
		Set<String> teachers = teachDao.getTeachersNames();
		
		for(String name : teachers) {
			System.out.println(name);
		}
	}*/
	
	@Test
	public void getTeacherScheduleByWeek() throws IOException {
		DatabaseFiller.fillSubjectsData();
	
		TeacherDAO teachDao = new TeacherDAO();
		
		//System.out.println("Розклад викладача доц. A. М. Глибовець");
		Set<Map<String, String>> sched = teachDao.getTeacherScheduleByWeek("доц. А.М. Глибовець", 14);
		
		for(Map<String, String> sch : sched) {
			for (Map.Entry<String, String> entry : sch.entrySet()) {
		        System.out.println(entry.getKey() + ": " + entry.getValue());
		    }
			
			System.out.println("********************");
		}
	}

}
