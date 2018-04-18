package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import parsers.AuditoriesParser;
import parsers.TimetableParser;
import vo.Auditorium;
import vo.TeacherSubjectInfo;

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
	    
	    //ArrayList<TeacherSubjectInfo> subjs;
	    
	    /*for(String spec : getAllFiles()) {
	    	subjs = timetPars.parseFile(spec);
	    	System.out.println("Size: "+subjs.size());
	    	teachDao.insertSubjectsData(subjs);
	    }*/
		
		/*ArrayList<String> files = getAllFiles();
		for(String file : files) {
			System.out.println(file);
		}*/
	}
	
	private static ArrayList<String> getAllFiles() {
		String folderPath = "F:/Магістерка/Info_retr/Practices/workspace/Redis_schedule/files_to_parse/schedule";
		
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

}
