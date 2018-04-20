package ua.edu.ukma.thkeys.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.edu.ukma.thkeys.vo.Auditorium;

public class AuditoriesParser {
	
	//���������� ��� ������� �����������
	//�����, ������, �� ����. ����
	public ArrayList<Auditorium> parseFile(String filename) throws IOException {
		Document doc = Jsoup.parse(new File(filename), "UTF-8");
		Element table = doc.select("table").get(0); //select the first table.
		Elements rows = table.select("tr");
		
		ArrayList<Auditorium> auds = new ArrayList<Auditorium>();

		for (int i = 1; i < rows.size(); i++) {
			Element row = rows.get(i);
		    Elements cols = row.select("td");
		    
		    Auditorium aud = new Auditorium();
		    aud.setRoom_no(cols.get(0).text());
		    aud.setCampus_no(aud.getRoom_no().split("-")[0]);
		    
		    if(cols.get(3).text().equals("���"))
		    	aud.setCompRoom(true);
		    else
		    	aud.setCompRoom(false);
		    
		    auds.add(aud);
		}
		
		/*for(Auditorium no : auds) {
			System.out.println(no);
			System.out.println("******************");
		}*/
		
		return auds;
	}
//	
//	public static void main(String[] args) throws IOException {
//		AuditoriesParser parser = new AuditoriesParser();
//		parser.parseFile("files_to_parse/auditories.html");
//	}

}
