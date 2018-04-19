package ua.edu.ukma.thkeys.dao;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

@Repository
public class LaborantDAO {
	
    @Autowired
    private Jedis jedis;
    
	//����������: ����, ����, ��������. ����������: ��������, ����
	public Map<String, String> getTeacherRoomDetails(int day_week_no, int pair_no, int campus_no, int room_no) {
	    System.out.println("Connection to server sucessfully");
	    
	    Map<String, String> dets = jedis.hgetAll("teacher_room_det:"+day_week_no+":"+pair_no+":"+campus_no+"-"+room_no);
	    
	    return dets;
	}

}
