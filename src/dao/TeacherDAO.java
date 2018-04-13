package dao;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class TeacherDAO {
	
	//Групування: день, пара, час. Інформація: аудиторія, предмет,тип, спеціальність, курс, група, тижні
	public Map<String, String> getSubjectDetails(int day_week_no, int pair_no, int hrs, int mins) {
		Jedis jedis = new Jedis("localhost"); 
	    System.out.println("Connection to server sucessfully");
	    
	    Map<String, String> dets = jedis.hgetAll("teacher_subj_det:"+day_week_no+":"+pair_no+":"+hrs+"-"+mins);
	    
	    jedis.close();
	    
	    return dets;
	}

}
