package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import vo.Auditorium;
import vo.TeacherSubjectInfo;

public class TeacherDAO {
	
	public void insertSubjectsData(ArrayList<TeacherSubjectInfo> subjects) {
		Jedis jedis = new Jedis("localhost"); 
	    //System.out.println("Connection to server sucessfully");
	    
	    String pairKey = "pk";
	    
	    for(TeacherSubjectInfo subj : subjects) {
	    	
	    	Map<String, String> subj_dets = new HashMap<String, String>();
	    	subj_dets.put("auditorium", subj.getAuditorium());
	    	subj_dets.put("subject_name", subj.getSubjectName());
	    	subj_dets.put("group", subj.getGroup());
	    	subj_dets.put("weeks", subj.getWeeks());
	    	subj_dets.put("speciality and year", subj.getSpec_year());
	    	subj_dets.put("day", subj.getDayName());
	    	subj_dets.put("time", subj.getTime());
	    	
	    	//jedis.hmset("teacher_subj_det:"+subj.getDayName()+":"+convertTimeToPairNo(subj.getTime()), subj_dets);
	    	
	    	pairKey = "teacher_subject_det:"+subj.getSpec_year()+":"+subj.getDayName()+":"+convertTimeToPairNo(subj.getTime());
	    	
	    	//Заповнюємо деталі про пару
	    	jedis.hmset(pairKey, subj_dets);
	    	
	    	//Додаємо пару до розкладу даного викладача
	    	jedis.sadd("teacher_schedule:"+subj.getTeacher(), pairKey);
	    }
	    
	    System.out.println("Data was inserted successfully!");
	    
	    jedis.close();
	}
	
	//Групування: день, пара, час. Інформація: аудиторія, предмет,тип, спеціальність, курс, група, тижні
	/*public Map<String, String> getSubjectDetails(String specYear, String day_week_name, int pair_no) {
		Jedis jedis = new Jedis("localhost"); 
	    System.out.println("Connection to server sucessfully");
	    
	    Map<String, String> dets = jedis.hgetAll("teacher_subject_det:"+specYear+":"+day_week_name+":"+pair_no);
	    
	    jedis.close();
	    
	    return dets;
	}*/
	
	public Set<Map<String, String>> getTeacherSchedule(String teachName) {
		Set<String> resTemp = new HashSet<String>();
		Set<Map<String, String>> res = new HashSet<Map<String, String>>();
		
		Jedis jedis = new Jedis("localhost");
		resTemp = jedis.smembers("teacher_schedule:"+teachName);
		
		//Дістали список ключів до пар
		for(String s : resTemp) {
			res.add(jedis.hgetAll(s));
		}
		
		jedis.close();
		
		return res;
	}
	
	private int convertTimeToPairNo(String time) {
		if(time.equals("8:30-9:50")) return 1;
		if(time.equals("10:00-11:20")) return 2;
		if(time.equals("11:40-13:00")) return 3;
		if(time.equals("13:30-14:50")) return 4;
		if(time.equals("15:00-16:20")) return 5;
		if(time.equals("16:30-17:50")) return 6;
		if(time.equals("18:00-19:20")) return 7;
		
		return -1;
	}

}
