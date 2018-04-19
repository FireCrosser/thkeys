package ua.edu.ukma.thkeys.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ua.edu.ukma.thkeys.parsers.AuditoriesParser;
import ua.edu.ukma.thkeys.parsers.TimetableParser;
import ua.edu.ukma.thkeys.vo.Auditorium;
import ua.edu.ukma.thkeys.vo.TeacherSubjectInfo;

public class DatabaseFiller {
	
	public static void fillClassroomsData() throws IOException {
		//Parsing auditories data
		AuditoriesParser parser = new AuditoriesParser();
		ArrayList<Auditorium> auds = parser.parseFile("files_to_parse/auds/auditories.html");
		ClassroomDAO clDao = new ClassroomDAO();
		
		//inserting in Redis parsed information
		clDao.insertClassroomsData(auds);
	}
	
	public static void fillSubjectsData() throws IOException {
		//Parsing schedule data
	    TimetableParser timetPars = new TimetableParser();
	    TeacherDAO teachDao = new TeacherDAO();
	    
	    /*ArrayList<TeacherSubjectInfo> subjs = timetPars.parseFile("files_to_parse/schedule/timetable_ipz_2.html"); 
	    teachDao.insertSubjectsData(subjs);*/
	    ArrayList<TeacherSubjectInfo> subjs;
	    
	    for(String spec : getAllFiles()) {
	    	subjs = timetPars.parseFile(spec);
	    	//System.out.println("Size: "+subjs.size());
	    	teachDao.insertSubjectsData(subjs);
	    }
		
		/*ArrayList<String> files = getAllFiles();
		for(String file : files) {
			System.out.println(file);
		}*/
	}
	
	/*public static void fillTeacherNamesData() throws IOException {
		TimetableParser timetPars = new TimetableParser();
	    TeacherDAO teachDao = new TeacherDAO();
	    
	    Set<String> teachs;
	    
	    for(String spec : getAllFiles()) {
	    	teachs = timetPars.parseTeacherNames(spec);
	    	//System.out.println("Size: "+subjs.size());
	    	teachDao.insertTeacherNames(teachs);
	    }
	    
	}*/
	
	private static ArrayList<String> getAllFiles() {
		String folderPath = "F:/���������/Info_retr/Practices/workspace/Redis_schedule/files_to_parse/schedule";
		
		ArrayList<String> res = new ArrayList<String>();
		
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        res.add(listOfFiles[i].getAbsolutePath());
	      }
	    }
		    
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		fillSubjectsData();
	}

}
