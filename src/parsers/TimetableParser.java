package parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vo.TeacherSubjectInfo;

public class TimetableParser {
	
	//ѕовертаЇ розклад у вигл€д≥ об'Їкт≥в
	//1 об'Їкт - 1 пара
	public ArrayList<TeacherSubjectInfo> parseFile(String filename) throws IOException {
		Document doc = Jsoup.parse(new File(filename), "UTF-8");
		Element table = doc.select("table").get(0); //select the first table.
		Elements rows = table.select("tr");
		
		String dayWeek = "YYYY";
		String time = "TTTTTT";
		
		ArrayList<TeacherSubjectInfo> infos = new ArrayList<TeacherSubjectInfo>();
		
		//System.out.println("Speciality: "+rows.get(6).text());
		
		for (int i = 10; i < rows.size(); i++) {
			
			Element row = rows.get(i);
		    Elements cols = row.select("td");
		    
		    if(!(cols.get(0).text().equals(""))) dayWeek = cols.get(0).text();
		    if(!(cols.get(1).text().equals(""))) time = cols.get(1).text();
		    
		    TeacherSubjectInfo info = new TeacherSubjectInfo();
		    
		    //якщо в цей день б≥льше немаЇ предмет≥в, переходимо на наступний день
		    if(cols.get(2).text().equals("")) continue;
		    else info.setSubjectName(cols.get(2).text());
		    
		    if(cols.get(0).text().equals(""))
		    	info.setDayName(dayWeek);
		    else
		    	info.setDayName(cols.get(0).text());
		    
		    if(cols.get(1).text().equals(""))
		    	info.setTime(time);
		    else
		    	info.setTime(cols.get(1).text());
		    
		    info.setTeacher(cols.get(3).text());
		    info.setGroup(cols.get(4).text());
		    info.setWeeks(cols.get(5).text());
		    info.setAuditorium(cols.get(6).text());
		    info.setSpec_year(rows.get(6).text());
		    
		    infos.add(info);
		}
		
		return infos;
	}
	
	public static void main(String[] args) throws IOException {
		TimetableParser parser = new TimetableParser();
		ArrayList<TeacherSubjectInfo> infos = parser.parseFile("files_to_parse/schedule/timetable_ipz_2.html");
		
		for(TeacherSubjectInfo inf : infos) {
			System.out.println(inf);
		}
	}
	
}
