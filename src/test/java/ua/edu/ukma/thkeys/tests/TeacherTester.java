package ua.edu.ukma.thkeys.tests;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.ukma.thkeys.ContextTest;

import ua.edu.ukma.thkeys.dao.DatabaseFiller;
import ua.edu.ukma.thkeys.dao.TeacherDAO;

public class TeacherTester extends ContextTest{
	
    @Autowired
    public DatabaseFiller databaseFiller;
    
    @Autowired
    public TeacherDAO teachDao;
    
//	@Test
	public void getTeacherSchedule() throws IOException {
		databaseFiller.fillSubjectsData();
		
		//System.out.println("������� ��������� ���. A. �. ���������");
		Set<Map<String, String>> sched = teachDao.getTeacherSchedule("���. �.�. ���������");
		
		for(Map<String, String> sch : sched) {
			for (Map.Entry<String, String> entry : sch.entrySet()) {
		        System.out.println(entry.getKey() + ": " + entry.getValue());
		    }
			
			System.out.println("********************");
		}
	}
	
	@Test
	public void getTeacherNames() throws IOException {
		databaseFiller.fillSubjectsData();
		
		Set<String> teachers = teachDao.getTeachersNames();
		
		for(String name : teachers) {
                    System.out.println(name);
		}
	}
	
//	@Test
	public void getTeacherScheduleByWeek() throws IOException {
		databaseFiller.fillSubjectsData();
		
		//System.out.println("������� ��������� ���. A. �. ���������");
		Set<Map<String, String>> sched = teachDao.getTeacherScheduleByWeek("���. �.�. ���������", 14);
		
		for(Map<String, String> sch : sched) {
			for (Map.Entry<String, String> entry : sch.entrySet()) {
		        System.out.println(entry.getKey() + ": " + entry.getValue());
		    }
			
			System.out.println("********************");
		}
	}

}
