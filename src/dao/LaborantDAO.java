package dao;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class LaborantDAO {
	
	//Групування: день, пара, аудиторія. Інформація: викладач, тижні
	public Map<String, String> getTeacherRoomDetails(int day_week_no, int pair_no, int campus_no, int room_no) {
		Jedis jedis = new Jedis("localhost"); 
	    System.out.println("Connection to server sucessfully");
	    
	    Map<String, String> dets = jedis.hgetAll("teacher_room_det:"+day_week_no+":"+pair_no+":"+campus_no+"-"+room_no);
	    
	    jedis.close();
	    
	    return dets;
	}

}
